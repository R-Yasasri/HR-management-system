<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="favicon.ico">
        <title>Login</title>
        <!-- Simple bar CSS -->
        <link rel="stylesheet" href="css/simplebar.css">
        <!-- Fonts CSS -->
        <link href="https://fonts.googleapis.com/css2?family=Overpass:ital,wght@0,100;0,200;0,300;0,400;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,600;1,700;1,800;1,900&display=swap" rel="stylesheet">
        <!-- Icons CSS -->
        <link rel="stylesheet" href="css/feather.css">
        <!-- Date Range Picker CSS -->
        <link rel="stylesheet" href="css/daterangepicker.css">
        <!-- App CSS -->
        <link rel="stylesheet" href="css/app-light.css" id="lightTheme">
        <link rel="stylesheet" href="css/app-dark.css" id="darkTheme" disabled>

        <style type="text/css">

            body{
                overflow: hidden;
            }

        </style>
    </head>
    <body class="light ">

        <div class="wrapper vh-100">
            <div class="row align-items-center h-100">
                <form class="col-lg-3 col-md-4 col-10 mx-auto text-center" method="POST" action="Login">
                    <a class="navbar-brand mx-auto mt-2 flex-fill text-center" href="./index.html">
                        <img src="assets/images/logo.png" id="ayuSeekLogo" height="120px" width="120px">
                    </a>
                    <h1 class="h6 mb-3">Sign in</h1>
                    <div class="form-group">
                        <label for="inputEmail" class="sr-only">Email address</label>
                        <input type="text" id="inputEmail" class="form-control form-control-lg" placeholder="User name" required="" autofocus="" name="username">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword" class="sr-only">Password</label>
                        <input type="password" id="inputPassword" class="form-control form-control-lg" placeholder="Password" required="" name="pass">
                    </div>
                    <div class="checkbox mb-3">
                        <label>
                            <input type="checkbox" value="remember-me"> Stay logged in </label>
                    </div>
                    <button class="btn btn-lg btn-success btn-block" type="submit">Let me in</button>
                    <p class="mt-5 mb-3 text-muted">© 2021 HRM</p>
                </form>
            </div>
        </div>



        <%@include file="modal.jsp" %>


        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/moment.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/simplebar.min.js"></script>
        <script src='js/daterangepicker.js'></script>
        <script src='js/jquery.stickOnScroll.js'></script>
        <script src="js/tinycolor-min.js"></script>
        <script src="js/config.js"></script>
        <script src="js/apps.js"></script>
        <!-- Global site tag (gtag.js) - Google Analytics -->
        <script async src="https://www.googletagmanager.com/gtag/js?id=UA-56159088-1"></script>
        <script>
            window.dataLayer = window.dataLayer || [];

            function gtag()
            {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());
            gtag('config', 'UA-56159088-1');
        </script>

        <%

            if (session.getAttribute("error") != null) {

        %>    

        <script type="text/javascript">

            showErrorModal('<%= session.getAttribute("error")%>');

        </script>  

        <%
//                System.out.println(session.getAttribute("error"));
                session.removeAttribute("error");
            }

        %>
    </body>
</html>
</body>
</html>