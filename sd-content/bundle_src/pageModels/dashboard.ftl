<#assign wp=JspTaglibs["/aps-core"]><#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>    
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title><@wp.currentPage param="title" /> - <@wp.i18n key="SD_STANDARD_DEMO" /></title>
    
    <meta name="viewport" content="width=device-width, user-scalable=no"/>
    
    <meta name="description" content=""/>
    
    <meta name="author" content=""/>
    
    <link rel="icon" href="<@wp.info key="systemParam" paramName="applicationBaseURL" /> favicon.png" type="image/png" />
    <@wp.fragment code="standard_demo_inclusions" escapeXml=false />
</head>
<body>
    <@wp.fragment code="keycloak_auth" escapeXml=false/>
    <div class="custom-navbar">
        <!--Navbar-->
        <nav class="navbar fixed-top navbar-expand-lg navbar-light">
            <!-- Navbar brand -->
            <@wp.show frame=0 />
            <!-- Collapse button -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-mobile"
            aria-controls="navbar-mobile" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <!-- Collapsible content -->
            <div class="collapse navbar-collapse" id="navbar-mobile">
                <!-- Links -->
                <@wp.show frame=1 />
                <ul class="navbar-nav mr-auto">
                    <!-- Dropdown -->
                    <li class="nav-item"><a class="nav-link" href="<@wp.url page="homepagesd" />">Home</a></li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Checking & Savings</a>
                        <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
                            <div class="dropdown-content-wrapper">
                                <div class="row dropdown-wrapper">
                                    <div class="col-6 dropdown-col"><a class="checking" href="<@wp.url page="interest" />"><span>Interest Checking</span></a>
                                        <div class="savings">High Yield Savings</div>
                                    </div>
                                    <div class="col-6 dropdown-right">
                                        <div class="debit-cards">Debit Cards</div>
                                        <div class="send-money">Send Money</div>
                                    </div>
                                </div>
                                <div class="row justify-content-around dropdown-wrapper-footer">
                                    <div class="col-6 dropdown-footer-heading"> Company
                                        <div class="row">
                                            <div class="col-6 dropdown-footer-content">
                                                <div>About</div>
                                                <div>Service</div>
                                                <div>Careers</div>
                                                <div>Blog</div>
                                            </div>
                                            <div class="col-6 dropdown-footer-content">
                                                <div>Media</div>
                                                <div>Terms</div>
                                                <div>Privacy</div>
                                                <div>Cookies</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-6 dropdown-footer-heading"> Customers
                                        <div class="row">
                                            <div class="col-12 col-lg-6 dropdown-footer-content">
                                                <div>Travel</div>
                                                <div>Pricing</div>
                                                <div>Account</div>
                                                <div>Help</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="<@wp.url page="credit_card" />">Credit Cards</a></li>
                    <li class="nav-item"><a class="nav-link" href="<@wp.url page="seed_insurances" />">Insurances</a></li>
                    <#if (Session.currentUser != "guest")>
                        <li class="nav-item">
                            <a class="nav-link" href="<@wp.url page="dashboard" />"><@wp.i18n key="SD_MY_DASHBOARD" /></a>
                        </li>
                    </#if>
                </ul>
                <div class="d-block d-sm-block d-md-none">
                    <@wp.show frame=2 />
                    <@wp.show frame=3 />
                </div>
            </div>
            <div class="d-none d-lg-block d-xl-block">
                <@wp.show frame=2 />
                <@wp.show frame=3 />
            </div>
            <!-- Collapsible content -->
        </nav>
        <div class="dashboard-container">
            <h1 class="current-page"><@wp.currentPage param="title" /></h1>
            <div class="row">
                <div class="col-12 col-lg-4"><@wp.show frame=4 /></div>
                <div class="col-12 col-lg-4"><@wp.show frame=5 /></div>
                <div class="col-12 col-lg-4"><@wp.show frame=6 /></div>
            </div>
            <div class="row">
                <div class="NotificationBar">
                    <@wp.show frame=8 />
                    <@wp.show frame=9 />
                    <@wp.show frame=10 />
                    <div class="NotificationBar__btn">
                        <@wp.show frame=7 />
                    </div>
                    <div class="NotificationBar__btn"><img class="CheckingCard__icon" src="<@wp.resourceURL />standard-demo/static/img/dashboard/Framepay.png"/>
                        Pay bills
                    </div>
                    <div class="NotificationBar__btn"><img class="CheckingCard__icon" src="<@wp.resourceURL />standard-demo/static/img/dashboard/Framemoney.png"/>
                        Send money
                    </div>
                    <div class="NotificationBar__btn"><img class="CheckingCard__icon" src="<@wp.resourceURL />standard-demo/static/img/dashboard/Frameinvest.png"/>
                        Investments
                    </div>
                    <div class="NotificationBar__btn"> <@wp.show frame=11 /> </div>
                    <div class="NotificationBar__btn">  <img class="CheckingCard__icon" src="<@wp.resourceURL />standard-demo/static/img/dashboard/Frametransfer.png"/>
                        Transfers
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <@wp.show frame=13 />
                </div>
            </div>
            <div class="row">
                <@wp.show frame=14 />
            </div>
            <div class="row">
                <@wp.show frame=15 />
            </div>
        </div>
        <@wp.show frame=16 />
        <@wp.show frame=17 />
    </div>
    <@wp.fragment code="insurance_inclusions_footer" escapeXml=false />
    <script nonce="<@wp.cspNonce/>" type="text/javascript" src="<@wp.resourceURL />static/js/datatables.min.js"></script>
</body>

</html>