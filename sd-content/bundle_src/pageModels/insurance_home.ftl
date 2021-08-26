<#assign wp=JspTaglibs["/aps-core"]>
<#assign c=JspTaglibs["http://java.sun.com/jsp/jstl/core"]>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>
        <@wp.currentPage param="title" /> - <@wp.i18n key="SD_STANDARD_DEMO_INSURANCE" />
    </title>
    <meta name="viewport" content="width=device-width,  user-scalable=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <link rel="icon" href="<@wp.info key="systemParam" paramName="applicationBaseURL" />favicon.png" type="image/png" />
    <@wp.fragment code="insurance_inclusions" escapeXml=false />
</head>
<body class="insurance">
<@wp.fragment code="keycloak_auth" escapeXml=false/>
<!--Navbar-->
<nav class="navbar first-nav navbar-expand-lg navbar-light ">
    <!-- Collapsible content -->
    <div class="collapse navbar-collapse navbars" id="basicExampleNav">
        <div class="first-header">
            <div class="back-button">
                <@wp.show frame=0 />
            </div>
            <div class="search-language">
                <@wp.show frame=1 />
                <@wp.show frame=2 />
            </div>
        </div>
    </div>
    <!-- Collapsible content -->
</nav>
<nav class="navbar second-nav navbar-expand-lg navbar-light ">
    <@wp.show frame=3 />
    <!-- Collapse button -->
    <button class="navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target=".navbars" aria-controls="basicExampleNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <!-- Collapsible content -->
    <div class="collapse navbar-collapse navbars" id="basicExampleNav2">
        <@wp.show frame=4 />
        <!-- Links -->
        <ul class="navbar-nav ml-auto">
            <@wp.show frame=5 />
        </ul>
        <div class="d-block d-sm-block d-md-none ">
            <@wp.show frame=1 />
            <@wp.show frame=2 />
        </div>
    </div>
    <!-- Collapsible content -->
</nav>
<!--/.Navbar-->
<@wp.show frame=6 />
<@wp.show frame=7 />
<@wp.show frame=8 />
<@wp.show frame=9 />
<@wp.show frame=10 />
<@wp.show frame=11 />
<@wp.show frame=12 />
<@wp.show frame=13 />
<@wp.show frame=14 />
<@wp.fragment code="insurance_inclusions_footer" escapeXml=false />
</body>
</html>