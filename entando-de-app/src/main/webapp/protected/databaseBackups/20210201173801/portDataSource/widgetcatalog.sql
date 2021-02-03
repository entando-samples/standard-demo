INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('messages_system','<?xml version="1.0" encoding="UTF-8"?><properties><property key="en">System Messages</property><property key="it">Messaggi di Sistema</property></properties>',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('login_form','<?xml version="1.0" encoding="UTF-8"?><properties><property key="en">Login Form</property><property key="it">Form di Login</property></properties>',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('formAction','<?xml version="1.0" encoding="UTF-8"?><properties><property key="en">Internal Servlet</property><property key="it">Invocazione di una Servlet Interna</property></properties>','<config>	<parameter name="actionPath">		Path to an action or to a JSP. You must prepend ''/ExtStr2'' to any Struts2 action path	</parameter>	<action name="configSimpleParameter"/></config>',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('entando_apis','<?xml version="1.0" encoding="UTF-8"?><properties><property key="en">APIs</property><property key="it">APIs</property></properties>',NULL,NULL,'formAction','<?xml version="1.0" encoding="UTF-8"?><properties><property key="actionPath">/ExtStr2/do/Front/Api/Resource/list.action</property></properties>',1,'free',NULL,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('userprofile_editCurrentUser','<?xml version="1.0" encoding="UTF-8"?><properties><property key="en">Edit Current User</property><property key="it">Edita Utente Corrente</property></properties>',NULL,NULL,'formAction','<?xml version="1.0" encoding="UTF-8"?><properties><property key="actionPath">/ExtStr2/do/Front/CurrentUser/edit.action</property></properties>',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('userprofile_editCurrentUser_password','<?xml version="1.0" encoding="UTF-8"?><properties><property key="en">Edit Current User Password</property><property key="it">Edita Password Utente Corrente</property></properties>',NULL,NULL,'formAction','<?xml version="1.0" encoding="UTF-8"?><properties><property key="actionPath">/ExtStr2/do/Front/CurrentUser/editPassword.action</property></properties>',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('userprofile_editCurrentUser_profile','<?xml version="1.0" encoding="UTF-8"?><properties><property key="en">Edit Current User Profile</property><property key="it">Edita Profilo Utente Corrente</property></properties>',NULL,NULL,'formAction','<?xml version="1.0" encoding="UTF-8"?><properties><property key="actionPath">/ExtStr2/do/Front/CurrentUser/Profile/edit.action</property></properties>',1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('content_viewer','<?xml version="1.0" encoding="UTF-8"?><properties><property key="en">Contents - Publish a Content</property><property key="it">Contenuti - Pubblica un Contenuto</property></properties>','<config>	<parameter name="contentId">Content ID</parameter>	<parameter name="modelId">Content Model ID</parameter>	<action name="viewerConfig"/></config>','jacms',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('search_result','<?xml version="1.0" encoding="UTF-8"?><properties><property key="en">Search - Search Result</property><property key="it">Ricerca - Risultati della Ricerca</property></properties>',NULL,'jacms',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('content_viewer_list','<?xml version="1.0" encoding="UTF-8"?><properties><property key="en">Contents - Publish a List of Contents</property><property key="it">Contenuti - Pubblica una Lista di Contenuti</property></properties>','<config>	<parameter name="contentType">Content Type (mandatory)</parameter>	<parameter name="modelId">Content Model</parameter>	<parameter name="userFilters">Front-End user filter options</parameter>	<parameter name="category">Content Category **deprecated**</parameter>	<parameter name="categories">Content Category codes (comma separeted)</parameter>	<parameter name="orClauseCategoryFilter" />	<parameter name="maxElemForItem">Contents for each page</parameter>	<parameter name="maxElements">Number of contents</parameter>	<parameter name="filters" />	<parameter name="title_{lang}">Widget Title in lang {lang}</parameter>	<parameter name="pageLink">The code of the Page to link</parameter>	<parameter name="linkDescr_{lang}">Link description in lang {lang}</parameter>	<action name="listViewerConfig"/></config>','jacms',NULL,NULL,1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('row_content_viewer_list','<?xml version="1.0" encoding="UTF-8"?><properties><property key="en">Contents - Publish Contents</property><property key="it">Contenuti - Pubblica Contenuti</property></properties>','<config>	<parameter name="contents">Contents to Publish (mandatory)</parameter>	<parameter name="maxElemForItem">Contents for each page</parameter>	<parameter name="title_{lang}">Widget Title in lang {lang}</parameter>	<parameter name="pageLink">The code of the Page to link</parameter>	<parameter name="linkDescr_{lang}">Link description in lang {lang}</parameter>	<action name="rowListViewerConfig" /></config>','jacms',NULL,NULL,1,'free',NULL,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('footer','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Footer</property>
<property key="it">Footer</property>
</properties>',NULL,NULL,NULL,NULL,0,'free',NULL,NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('Brand-Logo','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Brand Logo</property>
<property key="it">Brand Logo</property>
</properties>',NULL,NULL,NULL,NULL,0,'free',NULL,NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('apply_form_banner','<?xml version="1.0" encoding="UTF-8"?><properties><property key="en">Apply form banner</property><property key="it">Apply form banner</property></properties>',NULL,NULL,NULL,NULL,0,'free',NULL,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('credit_card_user_apply_form','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Credit card user apply form</property>
<property key="it">Form di applicazione carta di credito</property>
</properties>',NULL,NULL,NULL,NULL,0,'free',NULL,NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('find-atm','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Find ATM</property>
<property key="it">Trova ATM</property>
</properties>',NULL,NULL,NULL,NULL,0,'free',NULL,NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('signup','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Signup</property>
<property key="it">Registrazione</property>
</properties>',NULL,NULL,NULL,NULL,0,'free',NULL,NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('seedscard-transaction-table','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Seedscard Transaction Table</property>
<property key="it">Seedscard Transaction Table</property>
</properties>',NULL,NULL,NULL,NULL,0,'free',NULL,NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('seeds_card','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Seeds Card</property>
<property key="it">Seeds Card</property>
</properties>',NULL,NULL,NULL,NULL,0,'free','{"customElement":"seeds-card-config","resources":["widgets/seeds-card-config/static/js/runtime.js","widgets/seeds-card-config/static/js/vendor.js","widgets/seeds-card-config/static/js/main.js"]}',NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('list_item','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Alert Icon</property>
<property key="it">Icona di avviso</property>
</properties>',NULL,NULL,NULL,NULL,0,'free','{"customElement":"list-item-config","resources":["widgets/list-item/config/js/runtime.js","widgets/list-item/config/js/vendor.js","widgets/list-item/config/js/main.js"]}',NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('manage_users','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Manage Users</property>
<property key="it">Gestione Utenti</property>
</properties>',NULL,NULL,NULL,NULL,0,'free',NULL,NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('seeds_card_ng','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Seeds Card NG</property>
<property key="it">Seeds Card NG</property>
</properties>',NULL,NULL,NULL,NULL,0,'free','{"customElement":"seeds-card-config","resources":["widgets/seeds-card-config/static/js/runtime.js","widgets/seeds-card-config/static/js/vendor.js","widgets/seeds-card-config/static/js/main.js"]}',NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('entando-widget-navigation_bar','<?xml version="1.0" encoding="UTF-8"?><properties><property key="en">Navigation - Bar</property><property key="it">Navigazione - Barra Orizzontale</property></properties>','<config>	<parameter name="navSpec">Rules for the Page List auto-generation</parameter>	<action name="navigatorConfig" /></config>',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('language_choose','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Language choose</property>
<property key="it">Scelta lingua</property>
</properties>',NULL,NULL,NULL,NULL,0,'free',NULL,NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('back_to_seed','<?xml version="1.0" encoding="UTF-8"?><properties><property key="en">Back to  Seed bank</property><property key="it">Back to  Seed bank</property></properties>',NULL,NULL,NULL,NULL,0,'free','','',NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('search_form','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Search form</property>
<property key="it">Barra ricerca</property>
</properties>',NULL,NULL,NULL,NULL,0,'free',NULL,NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('brand_insurance','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Insurance logo</property>
<property key="it">Insurance logo</property>
</properties>',NULL,NULL,NULL,NULL,0,'free',NULL,NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('Login_buttons','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Seed Login buttons</property>
<property key="it">Seed Login buttons</property>
</properties>',NULL,NULL,NULL,NULL,0,'free',NULL,NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('insurance_login','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Insurance login</property>
<property key="it">Assicurazioni login</property>
</properties>',NULL,NULL,NULL,NULL,0,'free',NULL,NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('footer-insurance','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Insurance footer</property>
<property key="it">Footer Assicurazioni</property>
</properties>',NULL,NULL,NULL,NULL,0,'free',NULL,NULL,0,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid,readonlypagewidgetconfig,widgetcategory) VALUES ('Breadcrumb','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Breadcrumb</property>
<property key="it">Briciole di pane</property>
</properties>',NULL,NULL,NULL,NULL,0,'free',NULL,NULL,0,NULL);
