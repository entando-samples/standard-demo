INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (4,'BNR','Banner with accordion','<div class="main-banner-accordion">
    <div class="row">
        <div class="col-lg-6 col-xs-12">
            <div class="text-wrapper">
                <h1>$content.title.text</h1>
                <p>$content.subtitle.text</p>
                <div class="col-md-12 col-xs-12">
                    <img class="img-footer" src="$content.img.getImagePath(''0'')" alt="$content.img.text" />
                </div>
            </div>
        </div>

        <div class="col-lg-6 col-xs-12">
            <div class="accordion md-accordion " id="accordion-seed" role="tablist" aria-multiselectable="true">
                #foreach ($item in $content.accord)               
                <div class="card">
                    <div class="card-header" role="tab" id="id$item.idcard.value">
                        <a data-toggle="collapse" data-parent="#accordion-seed" href="#collapse-$item.idcard.value" aria-expanded="$item.open.text" aria-controls="collapse-$item.idcard.value">
                            <h2 class="mb-0">
                                <span class=" rotate-icon"></span>
                                <div class="card-header-left-margin">
                                  $item.headtitle.text
                                </div>  
                            </h2>
                        </a>
                    </div>
                    <div id="collapse-$item.idcard.value" class="collapse showloo" role="tabpanel" aria-labelledby="id$item.atitle.text" data-parent="#accordion-seed">
                        <div class="card-body">
                            <h3>$item.atitle.text</h3>
                            <p>$item.asubtitle.text</p>
                        </div>
                    </div>
                </div>
                #end
            </div>
        </div>
    </div>
</div>

<script nonce="<@wp.cspNonce />">
$(document).ready(function() {
  $(''#collapse-1'').addClass(''show'');
});
</script>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (8,'BNR','Credit - benefits & features','<div class="benefits-features">
    <div class="row">
        <div class="text-center col-12">
            <h1>$content.title.text</h1>
        </div>
        <div class="col-xs-12">
            <div class="row">
                #foreach ($item in $content.accord)  
                <div class="col-12 col-lg-4">
                    <div class="text-center">
                        <img class="benefits-features-logo" src="$item.aimg.getImagePath(''0'')" alt="$item.img.text" />
                        <h3>$item.atitle.text</h3>
                        <p>$item.asubtitle.text</p>
                    </div>
                </div>
                #end
            </div>
        </div>
    </div>
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (9,'BNR','Interest banner-landscape','<div class="banner-landscape">
 <img src=" $content.img.getImagePath(''0'')" alt="$content.img.text"  />
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (10,'BNR','Interest simple composite banner  ','<div class="interests-composite">
    <div class="row">
        <div class="col-12 interests-composite-main">           
                <h1>$content.title.text</h1><span>$content.subtitle.text</span> 
        </div>

        <div class="col-xs-12">
            <div class="row">
                #foreach ($item in $content.accord)  
                <div class="col-12">
                    <div class="text-center">
                        <h3>$item.atitle.text</h3>
                        <p>$item.asubtitle.text</p>
                    </div>
                </div>
                #end
            </div>
        </div>
    </div>
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (1,'BNR','Main Banner','<div class="main-banner">
    <div class="row">
        <div class="col-lg-6 col-xs-12 main-banner-center">
            <div class="text-wrapper">
                <h1> $content.title.text</h1>
                <p>$content.subtitle.text</p>
            </div>
        </div>
    </div>
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (3,'BNR','Banner with inner card','<div class="main-banner-12">
    <div class="main-banner-12-inner-wrapper">

        <div class="row">
            <div class="col-xs-12 col-lg-6">
                <div class="text-wrapper-inner">
                    <h2>$content.title.text</h2>
                    <p>$content.subtitle.text</p>
                    <p class="fine-print">$content.descr.text</p>
                </div>
            </div>

            <div class="col-lg-6 col-xs-12 image-center">
                <div class="centered-image">
                    <img src="$content.img.getImagePath(''0'')" alt="$content.img.text" />
                </div>
            </div>
        </div>

    </div>
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (5,'BNR','Banner  dark green background','<div class="main-banner-darkgreen ">
    <div class="row">
        <div class="col-lg-6 col-xs-12">
            <div class="text-wrapper">
                <h1>$content.title.text</h1>
                <p>$content.subtitle.text</p>
            </div>
        </div>
        <div class="col-lg-6 col-xs-12 image-center">
            <div class="centered-image">
                <img src="$content.img.getImagePath(''0'')" alt="$content.img.text" />
            </div>
        </div>
    </div>
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (7,'BNR','Credit light green	','<div class="credit-banner-green">
    <div class="row">
        <div class="col-12 col-lg-6 image-center">
            <div class="centered-image ">
                <img src=" $content.img.getImagePath(''0'')" alt="$content.img.text"  />
            </div>
        </div>
        <div class="col-12 col-lg-6 ">
            <div class="text-wrapper">
                <h1>$content.title.text</h1>
                <p>$content.subtitle.text</p>
            </div>
        </div>
    </div>
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (11,'BNR','Interest banner white','<div class="main-banner-white">
    <div class="row">
        <div class="col-lg-6 col-xs-12">
            <div class="text-wrapper">
                <h1>$content.title.text</h1>
                <p>$content.subtitle.text</p>              
        </div>
    </div>
    <div class="col-lg-6 col-xs-12">
        <div class="centered-image">
            <img  src="$content.img.getImagePath(''0'')" alt="$content.img.text" />
        </div>
    </div>
</div>
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (12,'BNR','Interest banner white img first','<div class="main-banner-white">
    <div class="row">
        <div class="col-lg-6 col-xs-12">
            <div class="centered-image">
                <img  src="$content.img.getImagePath(''0'')" alt="$content.img.text" />
            </div>
        </div>
        <div class="col-lg-6 col-xs-12">
            <div class="text-wrapper">
                <h1>$content.title.text</h1>
                <p>$content.subtitle.text</p>
        </div>
    </div>
</div>
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (13,'BNR','Interest 3 card title - 2nd banner','<div class="interest-features">
    <div class="row">
        <div class="text-center col-12">            
            <h1>$content.title.text</h1>
        </div>
        <div class="col-xs-12">
            <div class="row">
                #foreach ($item in $content.accord)  
                <div class="col-12 col-lg-4">
                    <div class="text-center">
                        <img class="benefits-features-logo" src="$item.aimg.getImagePath(''0'')" alt="$item.img.text" />
                        <h3>$item.atitle.text</h3>
                        <p>$item.asubtitle.text</p>
                    </div>
                </div>
                #end
            </div>
        </div>
    </div>
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (100,'MPC','Multi-Purpose Header','<div class="insurance-main-banner" style="background: url($content.image.getImagePath(''0''))">
    <div class="main-wrapper">
            <div class="text-wrapper">
                <h1> $content.title.text</h1>
                <p>$content.body.text</p>
                 
                 #foreach ($item in $content.links)     
                  <a class="btn btn-green" href="$item.destination" >$item.text</a>
                 #end
        </div>
    </div>
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (101,'MPC','Multi-Purpose Centered','<div class="insurance-centered-content" style="background: url($content.image.getImagePath(''0''))">
    <div class="row no-gutters">
        <div class="text-center col-12">
            <h2> $content.title.text</h2>
            <div class="subtitle">$content.subtitle.text</div>
            <div class="body">$content.body.text</div>

            #foreach ($item in $content.links)     
            <a class="btn btn-dark-green" href="$item.destination" >$item.text</a>
            #end
        </div>

    </div>
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (102,'MPC','Multi-Purpose Text Right - Image Left','<section class="image-on-left" style="background:#D8EAA0">
    <div class="img-wrapper">
        <div class="img_right">
            <figure>
                <img src="$content.image.getImagePath(''0'')" alt="$content.image.text">
            </figure>
        </div>
    </div>
    <div class="content-text-wrapper">
        <div class="box-centered">

            <h2> $content.title.text</h2>

            <div class="body">$content.body.text</div>

            #foreach ($item in $content.links)     
            <a class="btn btn-dark-green" href="$item.destination" >$item.text</a>
            #end

        </div>
    </div>
</section>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (103,'MPC','Multi-Purpose Card for 3 Columns  List','<div class="card-insurance">
    <img src="$content.image.getImagePath(''0'')"  alt="$content.image.text">
    
     $content.title.text
     $content.subtitle.text
     $content.body.text

    #if($content.links.size() > 0)
    #foreach ($item in $content.links)     
    <a class="btn btn-green" href="$item.destination" >$item.text</a>
    #end
    #end
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (104,'MPC','Multi-Purpose Text Left - Image Right','<section class="image-on-right" style="background:#184437">
    <div class="content-text-wrapper">
        <div class="box-centered">

            <h2> $content.title.text</h2>

            <div class="body">$content.body.text</div>

            #foreach ($item in $content.links)     
            <a class="btn btn-green" href="$item.destination" >$item.text</a>
            #end

        </div>
    </div>
    <div class="img-wrapper">
        <div class="img_right">
            <figure>
                <img src="$content.image.getImagePath(''0'')" alt="$content.image.text">
            </figure>
        </div>
    </div>
</section>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (105,'MPC','Multi-Purpose Card Product detail','<section class="image-on-right" style="background:#fff">
    <div class="content-text-wrapper">
        <div class="box-centered">

            <div class="category">
                #foreach ($contentCategory in $content.getCategories())
                $contentCategory.title
                #end 
            </div>

            <h2> $content.title.text</h2>

            <div class="body">$content.body.text</div>

            #foreach ($item in $content.links)     
            <a class="btn btn-dark-green" href="$item.destination" >$item.text</a>
            #end
            
             <a class="btn btn-dark-green" href="$content.getContentOnPageLink(''products_detail'')&modelId=105" >
                    $i18n.getLabel("READ_MORE")
             </a>
            

        </div>
    </div>
    <div class="img-wrapper">
        <div class="img_right">
            <figure>
                <img src="$content.image.getImagePath(''0'')" alt="$content.image.text">
            </figure>
        </div>
    </div>

</section>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (106,'MPC','Multi-Purpose Card 3 Columns  List with categories','<div class="card-insurance with-categories">
<a href="$content.getContentOnPageLink(''products_detail'')&modelId=105" class="card-clickable">
                                
    <img src="$content.image.getImagePath(''0'')"  alt="$content.image.text">
    
      <div class="category">
                #foreach ($contentCategory in $content.getCategories())
                $contentCategory.title
                #end 
     </div>
    
     $content.title.text
     $content.subtitle.text
     

    #if($content.links.size() > 0)
    #foreach ($item in $content.links)     
    <a class="btn btn-green" href="$item.destination" >$item.text</a>
    #end
    #end
   </a> 
 </div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (14,'NWS','News List','<div class="default-list-model">
    <div class="row no-gutters">

        #if ($content.img.getImagePath(''0'') != "")
        <div class="col-md-1 col-2 img-box">
            <img class="rounded img-fluid" src="$content.img.getImagePath(''0'')" alt="$content.img.text" />
        </div>
        #end


        <div class="col-md-11 col-10 info-box">
            <h3>$content.title.text</h3>
            <p>$content.subtitle.text</p>

            <a href="$content.getContentOnPageLink("detail_page")&modelId=15" class="btn apply">
               $i18n.getLabel("READ_MORE")
        </a>
    </div>
</div>
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (15,'NWS','News Details','<div class="search-result">
    <div class="default-detail-model">
        <div class="row no-gutters">

            #if ($content.img.getImagePath(''0'') != "")
            <div class="col-md-1 col-2 img-box">
                <img class="rounded img-fluid" src="$content.img.getImagePath(''0'')" alt="$content.img.text" />
            </div>
            #end
            <div class="col-md-11 col-10 info-box">
                <h3>$content.title.text</h3>
                <p>$content.subtitle.text</p>
            </div>
            <p>$content.body.text</p>

        </div> 

        <div class="col-12 well">
            $i18n.getLabel("LINKS")<br/>
            #if ($content.links != "")      
            #foreach ($item in $content.links)                            
            <a class="btn btn-outline-primary" href="$item.destination">$item.text     <i class="fas fa-link"></i></a>                           
            #end
            #end
        </div>
        <div class="col-12 well">
            $i18n.getLabel("ATTACHES")<br/>
            #if ($content.attaches != "")
            #foreach ($item in $content.attaches)                       
            <a class="btn btn-outline-default" href="$item.AttachPath">$item.text   <i class="fas fa-file-download"></i></a>
            #end
            #end
        </div>
    </div>
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (18,'NWS','news card','<div class="card card-dim" >
  <img class="card-img-top" src="$content.img.getImagePath(''0'')" alt="$content.img.text" />
  <div class="card-body">
    <h5 class="card-title">$content.title.text</h5>
    <p class="card-text">$content.subtitle.text</p>
    <a href="$content.getContentOnPageLink("detail_page")&modelId=15" class="btn apply">$i18n.getLabel("READ_MORE")</a>
  </div>
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (107,'PRD','Products 3 Columns  List with categories','<div class="card-insurance with-categories">
    <a href="$content.getContentOnPageLink(''product_details'')&modelId=108" class="card-clickable">

        <img src="$content.image.getImagePath(''0'')"  alt="$content.image.text">

        <div class="category">
            #foreach ($contentCategory in $content.getCategories())
            $contentCategory.title
            #end 
        </div>

        <p>$content.title.text</p>
        $content.subtitle.text


    </a> 
</div>
<script>

    $(''.category'').each(function() {
    var text = $(this).text();
    $(this).text(text.replace(''My Product'', '''')); 
    });

</script>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (109,'PRD','Products Text Left  - Image Right','<section class="image-on-right products" style="background:#fff">
    <div class="content-text-wrapper">
        <div class="box-centered">

            <div class="category">
                #foreach ($contentCategory in $content.getCategories())
                $contentCategory.title
                #end 
            </div>

            <h2> $content.title.text</h2>

            <div class="body">$content.subtitle.text</div>
        
            
             <a class="btn btn-dark-green" href="$content.getContentOnPageLink(''product_details'')&modelId=108" >
                    $i18n.getLabel("READ_MORE")
             </a>
            

        </div>
    </div>
    <div class="img-wrapper">
        <div class="img_right">
            <figure>
                <img src="$content.image.getImagePath(''0'')" alt="$content.image.text">
            </figure>
        </div>
    </div>

</section>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (2,'BNR',' Banner light green','<div class="main-banner-green">

    <div class="row">
        <div class="col-12 col-lg-6 ">
            <div class="text-wrapper">

                <h1>$content.title.text</h1>
                <p>$content.subtitle.text</p>
            </div>
        </div>
        <div class="col-12 col-lg-6 image-center">
            <div class="centered-image">
                <img src=" $content.img.getImagePath(''0'')" alt="$content.img.text"  />
            </div>
        </div>
    </div>
</div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (6,'BNR','Banner with white background','<div class="main-banner-white">
            <div class="row">
                <div class="col-lg-6 col-xs-12">
                    <div class="text-wrapper">
                        <h1>$content.title.text</h1>
                        <p>$content.subtitle.text</p>
                        <a href"$content.link.destination" target="#" class="btn btn-lg btn-cta">
                           Apply Now
                        </a>
                    </div>
                </div>
                <div class="col-lg-6 col-xs-12 image-center">
                    <div class="centered-image">
                        <img  src="$content.img.getImagePath(''0'')" alt="$content.img.text" />
                    </div>
                </div>
            </div>
        </div>',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (108,'PRD','Product detail','<section class="image-on-right detail" style="background:#fff">
    <div class="content-text-wrapper">
        <div class="box-centered">

            <div class="category">
                #foreach ($contentCategory in $content.getCategories())
                $contentCategory.title
                #end 
            </div>

            <h2>$content.title.text</h2>

            <div class="body">
                $content.subtitle.text
            </div>

            #foreach ($item in $content.paragraphs)     

            #if ($item.mtitle.text !="" ) 
            <div class="mtitle">
                $item.mtitle.text
            </div>
            #end
            #if ($item.mbody.text !="" ) 
            <div class="mbody">
                $item.mbody.text
            </div>
            #end
            #if ($item.mimage.text !="" ) 
            <div class="mimage">
                <img src="$item.mimage.getImagePath(''0'')"  alt="$item.mimage.text">
            </div>
            #end
            #end

            #foreach ($item in $content.links)     
            <a class="btn btn-dark-green" href="$item.destination" >$item.text</a>
            #end

            #foreach ($item in $content.attaches)     
            <a class="btn btn-attaches" href="$item.AttachPath" >$item.text<i class="fas fa-arrow-right"></i></a>
            #end

        </div>
    </div>
    <div class="img-wrapper">
        <div class="img_right">
            <figure>
                <img src="$content.image.getImagePath(''0'')" alt="$content.image.text">
            </figure>
        </div>
    </div>

</section>

<script>

    $(''.category'').each(function () {
        var text = $(this).text();
        $(this).text(text.replace(''My Product'', ''''));
    });

</script>',NULL);
