<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <!-- Required meta tags-->
     
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    

    <!-- Title Page-->
    <title>add student</title>

  
</head>

<body>

    <div class="container-fluid">
                <div class="card-body">
                    <h2 class="title">Change Product Details</h2>
                      <button class="btn btn--radius-2 btn--blue" value="${product.id}">${product.id}</button>
                    <form  action="${pageContext.request.contextPath}/submit"	 method="POST">
             	<input  type="text" value="${product.id }" name="Id">
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Product Name</label>
                                    <input class="input--style-4" type="text" name="Name" value="${product.name }">
                                </div>
                            </div>
                        </div>
                         <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Product Quantity</label>
                                    <input class="input--style-4" type="text" name="Quantity" value="${product.quantity}">
                                </div>
                            </div>
                             <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Product Price </label>
                                    <input class="input--style-4" type="text" name="Price" value="${product.price }">
                                </div>
                            </div>
                        <div class="p-t-15 text-center">
                            <button class="btn btn--radius-2 btn--blue" type="submit">Submit</button>
                       </div>
                       
                    </form>
                </div>
         
             <a href = "${pageContext.request.contextPath}/viewprod" class = "btn btn-info btn-outline-primary" role = "button">View Product </a>
          
          </div>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->