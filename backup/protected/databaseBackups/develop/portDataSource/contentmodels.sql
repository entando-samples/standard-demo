INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (1,'BNR','Main Banner','<div class="main-banner">
            <div class="row">
                <div class="col-lg-6 col-xs-12">
                    <div class="text-wrapper">
                        <h1> $content.title.text</h1>
                        <p>$content.subtitle.text</p>
                    </div>
                </div>
            </div>
        </div>
',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (3,'BNR','Banner with inner card','
<div class="main-banner-12">
            <div class="main-banner-12-inner-wrapper">

                <div class="row">
                    <div class="col-xs-12 col-lg-6">
                        <div class="text-wrapper-inner">
                            <h2>$content.title.text</h2>
                            <p>$content.subtitle.text</p>
                            <p class="fine-print">$content.descr.text</p>
                        </div>
                    </div>

                    <div class="col-lg-6 col-xs-12">
                        <div class="centered-image">
                            <img src="$content.img.getImagePath(''0'')" alt="$content.img.text" />
                        </div>
                    </div>
                </div>

            </div>
</div>
',NULL);
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
                <div class="col-lg-6 col-xs-12">
                    <div class="centered-image">
                        <img  src="$content.img.getImagePath(''0'')" alt="$content.img.text" />
                    </div>
                </div>
            </div>
        </div>
',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (5,'BNR','Banner with dark green background','<div class="main-banner-darkgreen ">
            <div class="row">
                <div class="col-lg-6 col-xs-12">
                    <div class="text-wrapper">
                        <h1>$content.title.text</h1>
                        <p>$content.subtitle.text</p>
                    </div>
                </div>
                <div class="col-lg-6 col-xs-12">
                    <div class="col-md-12 col-xs-12">
                        <div class="centered-image">
                            <img src="$content.img.getImagePath(''0'')" alt="$content.img.text" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        ',NULL);
INSERT INTO contentmodels (modelid,contenttype,descr,model,stylesheet) VALUES (2,'BNR',' Banner light green',' <div class="main-banner-green">

            <div class="row">
                <div class="col-12 col-lg-6 ">
                    <div class="text-wrapper">

                        <h1>$content.title.text</h1>
                        <p>$content.subtitle.text</p>
                    </div>
                </div>
                <div class="col-12 col-lg-6">
                    <div class="centered-image">
                        <img src=" $content.img.getImagePath(''0'')" alt="$content.img.text"  />
                    </div>
                </div>
            </div>
        </div>



        
     ',NULL);
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

<script>
$(document).ready(function() {
  $(''#collapse-1'').addClass(''show'');
});
</script>',NULL);
