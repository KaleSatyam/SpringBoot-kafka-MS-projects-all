<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <!-- Required meta tags-->
     <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">

    <!-- Title Page-->
    <title>add student</title>

    <!-- Icons font CSS-->
    <link href="views/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="views/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <!-- Font special for pages-->
    <link href="views/https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

    <!-- Vendor CSS-->
    <link href="views/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="views/vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link href="views/css/main.css" rel="stylesheet" media="all">
</head>

<body>

    <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
        <div class="wrapper wrapper--w680">
            <div class="card card-4">
                <div class="card-body">
                    <h2 class="title">Add Product</h2>
                    
                    <form  action="view"	method="POST" >
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Product Name</label>
                                    <input class="input--style-4" type="text" name="Name" placeholder="Enter product name">
                                </div>
                            </div>
                        </div>
                         <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Product Quantity</label>
                                    <input class="input--style-4" type="text" name="Quantity" placeholder="Enter product quantity">
                                </div>
                            </div>
                             <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Product Price </label>
                                    <input class="input--style-4" type="text" name="Price" placeholder="Enter product price">
                                </div>
                            </div>
                        <div class="p-t-15 text-center">
                            <button class="btn btn--radius-2 btn--blue" type="submit">Submit</button>
                         
                          
                        </div>
                       
                    </form>
                </div>
            </div>
            <div>
             <a href = "viewprod" class = "btn btn-info btn-outline-primary" role = "button">View Product </a>
             </div>
        </div>
    </div>

    <!-- Jquery JS-->
    <script src="views/vendor/jquery/jquery.min.js"></script>
    <!-- Vendor JS-->
    <script src="views/vendor/select2/select2.min.js"></script>
    <script src="views/vendor/datepicker/moment.min.js"></script>
    <script src="views/vendor/datepicker/daterangepicker.js"></script>

    <!-- Main JS-->
    <script src="views/js/global.js"></script>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->