
$(".txtOrderId").text(generateOrderNumber());
$("#cmbItemId").append("<option> Select </option>");
$("#cmbcustormerId").append("<option> Select </option>");


$("#btnAddtoCart").click(function () {
    let itemQty =parseInt($("#itemqty").val());
    let orderQty =parseInt($("#OrderQty").val());
    if($("#OrderQty").val() !=''){
        if(itemQty < orderQty ){
            alert("echara nane");
        }else{

            addToCart();
            ReduceItemQty($("#OrderQty").val());
            loadCartTable();

        }
    }else{
        alert("type karala nane");
    }
});



function generateOrderNumber() {

    $.ajax({
      //  url:"http://localhost:8080/PosSystem/order",
        method:"GET",
        success(response){
            try {
                let lastOId =response.data[response.data.length - 1].oD;
                let newOId = parseInt(lastOId.substring(1, 4)) + 1;
                console.log(lastOId);
                console.log(newOId)
                if (newOId < 10) {
                    $(".txtOrderId").text("O00" + newOId);
                } else if (newOId < 100) {
                    $(".txtOrderId").text("O0" + newOId);
                } else {
                    $(".txtOrderId").text("O" + newOId);
                }
            } catch (e) {
                $(".txtOrderId").text("O001");
            }
        }
    });
};



$(".comfirmOrders").click(function(){
   generateOrderNumber();
   saveAllOrders();
   //loadAllConfirmOrder();
   Carts=[];
   // clearAll();
});

function forAll(){
    loadCustIDs();
    loadItemIDs();
}






function loadItemIDs(){
    $("#cmbItemId").empty();
    // var customer=getCustomers();
    var ids=document.getElementById("cmbItemId");
    $.ajax({
     //   url:"http://localhost:8080/PosSystem/items",
        method:"GET",
        success(resp) {
            console.log(resp.data);
            for (var i of resp.data) {
                var opt=document.createElement("option");
                opt.value=i.itemCode;
                opt.text=i.itemCode;
                ids.appendChild(opt);
            }
        }
    });
}
$("#cmbItemId").click(function () {
    let itemId=$('#cmbItemId').val();
    $.ajax({
     //   url:"http://localhost:8080/PosSystem/items",
        method:"Get",
        success(resp){
            for (var i of resp.data) {
                if(itemId==i.itemCode){

                    $("#itNames").val(i.itemName);
                    $("#itemprice").val(i.price);
                    $("#itemqty").val(i.qty);
                }
            }
        }
    });
});






function loadCustIDs(){
    $("#cmbcustormerId").empty();
    // var customer=getCustomers();
    var ids=document.getElementById("cmbcustormerId");
    $.ajax({
       // url:"http://localhost:8080/PosSystem/customer",
        method:"GET",
        success(resp) {
            console.log(resp.data);
            for (var i of resp.data) {
                var opt=document.createElement("option");
                opt.value=i.id;
                opt.text=i.id;
                ids.appendChild(opt);
            }
        }
    });
}
$("#cmbcustormerId").click(function () {

    let cus=$('#cmbcustormerId').val();
    $.ajax({
      //  url:"http://localhost:8080/PosSystem/customer",
        method:"Get",
        success(resp){
            for (var i of resp.data) {
                if(cus==i.id){
                    $("#cusName").val(i.name);
                    $("#CusSalary").val(i.salary);
                    $("#CusAddress").val(i.address);

                }
            }
        }
    });
});










 function saveAllOrders(){
    let oId =$(".txtOrderId").text();
    let cusid =$("#cmbcustormerId").val();
    let date =$("#datetime").val();
    let discount =$("#txtDiscount").val();
    let totals =$("#txtTotal").text();





    var OrderOB = {
        "OrderID" : oId,
        "orderDate": date,
        "OrderTime": totals,
        "CustID": cusid,
        "cart": Carts
    }

    // console.log(OrderOB.cart[0].itemCode)



    $.ajax({
      //  url: "http://localhost:8080/PosSystem/order",
        method:"POST",
        contentType: "application/json",
        data:JSON.stringify(OrderOB),
        success:function (response) {
            if (response.status == 200) {
                generateOrderNumber();
                alert(response.data);
            }else{
                alert(response.data);
            }
        }
    });

 }



 function ReduceItemQty(orderQty){
     var qtyOn = parseInt(orderQty);
     var avilableQty = parseInt($("#itemqty").val());

     avilableQty = avilableQty - qtyOn;
     let  itemCode = $("#cmbItemId").val();

     var ob ={
          "qty" :$("#itemqty").val(),
          "itemCode" :$("#cmbItemId").val(),
     }



 };

 let sub_total = 0;
 let discount = 0;
 let total = 0;

 function Calculatetotal(orderQty,itemPrice,disc){
    sub_total+= orderQty * itemPrice;
    discount+= (orderQty * itemPrice *disc)  / 100;
    total = sub_total - discount;
    $("#txtTotal").text(total);
    $("#txtsubTotal").text(sub_total);
 }


function addToCart() {
  let  itemCode = $("#cmbItemId").val();
  let  itemName = $("#itNames").val();
  let  itemPrice = $("#itemprice").val();
  let  itemQty = $("#itemqty").val();
  let  orderQty = $("#OrderQty").val();
  let  disc = $("#txtDiscount").val();
  let total =  itemPrice * orderQty;
  let fulltoal = total;


   Calculatetotal($("#OrderQty").val(),$("#itemprice").val(),$("#txtDiscount").val());
  
    for(var i in Carts){
        if(Carts[i].itemCode===itemCode){
          var newqty  =+Carts[i].iqty +  +orderQty;
          var newTotal =itemPrice * newqty; 
          Carts[i].iqty=newqty;
          Carts[i].total=newTotal;
          return;
        }
    }

    cartsOB ={
        "itemCode":itemCode,
        "itemName":itemName,
        "itemPrice":itemPrice,
        "orderQty":orderQty,
        "total":total,
        "disc":disc,
        "fulltoal":fulltoal
    }

    Carts.push(cartsOB);

}

 function loadCartTable(){
    $("#cartTabale").empty();
    for(var i of Carts){
        let row = `<tr><td>${i.itemCode}</td><td>${i.itemName}</td><td>${i.orderQty}</td><td>${i.itemPrice}</td><td>${i.fulltoal}</td><td><button type="button" class="btn-sm  btnDeleteItem btn-danger">Delete</button>
        <button type="button"  data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn-sm border btn-success updaterow" style="width: 17%;  "><svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px"
        width="35" height="20"
        viewBox="0 0 172 172"
        style=" right:5px; position: relative; fill:#000000;"><g fill="none" fill-rule="nonzero" stroke="none" stroke-width="1" stroke-linecap="butt" stroke-linejoin="miter" stroke-miterlimit="10" stroke-dasharray="" stroke-dashoffset="0" font-family="none" font-weight="none" font-size="none" text-anchor="none" style="mix-blend-mode: normal"><path d="M0,172v-172h172v172z" fill="none"></path><g fill="#ffffff"><path d="M101.05,42.28333l-79.55,79.55v28.66667h28.66667l79.55,-79.55zM111.8,31.53333l17.2,-17.2l28.66667,28.66667l-17.2,17.2z"></path></g></g></svg></button></td>
        </tr>`
        $("#cartTabale").append(row);
    }
 }

   function clearAll(){
    $("#datetime").val('');
    $("#cmbcustormerId").val('');
    $("#cusName").val('');
    $("#CusSalary").val('');
    $("#CusAddress").val('');
    $("#txtTotal").text('');
    $("#txtsubTotal").text('');
   }

   function clearAddtoCart(){
      $("#cmbItemId").val('');
      $("#itNames").val('');
      $("#itemprice").val('');
      $("#itemqty").val('');
      $("#OrderQty").val('');
      $("#txtDiscount").val('');
   }


