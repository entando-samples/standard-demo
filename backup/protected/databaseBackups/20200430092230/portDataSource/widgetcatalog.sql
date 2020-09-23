INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('messages_system','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">System Messages</property>
<property key="it">Messaggi di Sistema</property>
</properties>',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('login_form','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Login Form</property>
<property key="it">Form di Login</property>
</properties>',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('formAction','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Internal Servlet</property>
<property key="it">Invocazione di una Servlet Interna</property>
</properties>','<config>
	<parameter name="actionPath">
		Path to an action or to a JSP. You must prepend ''/ExtStr2'' to any Struts2 action path
	</parameter>
	<action name="configSimpleParameter"/>
</config>',NULL,NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('entando_apis','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">APIs</property>
<property key="it">APIs</property>
</properties>
',NULL,NULL,'formAction','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="actionPath">/ExtStr2/do/Front/Api/Resource/list.action</property>
</properties>
',1,'free',NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('userprofile_editCurrentUser','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Edit Current User</property>
<property key="it">Edita Utente Corrente</property>
</properties>',NULL,NULL,'formAction','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="actionPath">/ExtStr2/do/Front/CurrentUser/edit.action</property>
</properties>',1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('userprofile_editCurrentUser_password','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Edit Current User Password</property>
<property key="it">Edita Password Utente Corrente</property>
</properties>',NULL,NULL,'formAction','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="actionPath">/ExtStr2/do/Front/CurrentUser/editPassword.action</property>
</properties>',1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('userprofile_editCurrentUser_profile','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Edit Current User Profile</property>
<property key="it">Edita Profilo Utente Corrente</property>
</properties>',NULL,NULL,'formAction','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="actionPath">/ExtStr2/do/Front/CurrentUser/Profile/edit.action</property>
</properties>',1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('bpm-datatype-form','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">PAM-Form by DataType</property>
<property key="it">PAM-Form by DataType</property>
</properties>','<config>
	<parameter name="dataTypeCode">Data Type Code</parameter>
	<parameter name="dataUxId">Data Ux ID</parameter>
	<parameter name="widgetInfoId">Widget Info ID</parameter>
	<action name="jpkiebpmBpmFormWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('bpm-datatype-task-form','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">PAM-Task Form by DataType</property>
<property key="it">PAM-Task Form by DataType</property>
</properties>','<config>
	<parameter name="dataTypeCode">Data Type Code</parameter>
	<parameter name="dataUxId">Data Ux ID</parameter>
	<parameter name="widgetInfoId">Widget Info ID</parameter>
	<action name="jpkiebpmBpmTaskFormWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('bpm-datatable-task-list','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">PAM-Datatable Task List</property>
<property key="it">PAM-Datatable Task List</property>
</properties>','<config>
	<parameter name="widgetInfoId">WidgetInfoID</parameter>
	<action name="jpkiebpmBpmTaskListDatatableWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('bpm-datatable-process-list','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">PAM-Process list</property>
<property key="it">PAM-Lista processi</property>
</properties>','<config>
	<parameter name="widgetInfoId">WidgetInfoID</parameter>
	<action name="jpkiebpmBpmProcessDatatableWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('bpm-case-progress-status','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">PAM-Case progress status</property>
<property key="it">PAM-Case stato del processo</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="showMilestones">showMilestones</parameter>
	<parameter name="showNumberOfTasks">showNumberOfTasks</parameter>
	<parameter name="progressBarType">progressBarType</parameter>
<action name="jpkiebpmBpmCaseProgressWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('bpm-case-instance-selector','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">PAM-Case instance selector</property>
<property key="it">Selettore di istanza Case PAM</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmCaseInstanceSelectorWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('bpm-case-comments','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">PAM-Case comments</property>
<property key="it">Commenti del caso PAM</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmCaseInstanceCommentsWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('bpm-case-details','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">PAM-Case details</property>
<property key="it">Dettagli caso PAM</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmCaseInstanceDetailsWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('bpm-case-actions','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">PAM-Case actions</property>
<property key="it">Azioni caso PAM</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmCaseInstanceActionsWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('bpm-case-roles','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">PAM-Case roles</property>
<property key="it">Ruoli caso PAM</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmCaseInstanceRolesWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('bpm-case-chart','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">PAM-Case chart</property>
<property key="it">PAM-Case chart</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmCaseInstanceChartWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('bpm-process-diagram','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">PAM-Process diagram</property>
<property key="it">Diagramma di processo PAM</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmProcessDiagramWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('bpm-case-file','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">PAM-Case file</property>
<property key="it">File caso PAM</property>
</properties>','<config>
        <parameter name="channel">channel</parameter>
	<parameter name="frontEndCaseData">frontEndCaseData</parameter>
	<action name="jpkiebpmBpmCaseInstanceFileWidgetViewerConfig"/>
</config>','jpkiebpm',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('bpm-task-details','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">PAM-Task details</property>
<property key="it">Dettagli task PAM</property>
</properties>',NULL,'jpkiebpm',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('content_viewer','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Contents - Publish a Content</property>
<property key="it">Contenuti - Pubblica un Contenuto</property>
</properties>','<config>
	<parameter name="contentId">Content ID</parameter>
	<parameter name="modelId">Content Model ID</parameter>
	<action name="viewerConfig"/>
</config>','jacms',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('search_result','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Search - Search Result</property>
<property key="it">Ricerca - Risultati della Ricerca</property>
</properties>',NULL,'jacms',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('content_viewer_list','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Contents - Publish a List of Contents</property>
<property key="it">Contenuti - Pubblica una Lista di Contenuti</property>
</properties>','<config>
	<parameter name="contentType">Content Type (mandatory)</parameter>
	<parameter name="modelId">Content Model</parameter>
	<parameter name="userFilters">Front-End user filter options</parameter>
	<parameter name="category">Content Category **deprecated**</parameter>
	<parameter name="categories">Content Category codes (comma separeted)</parameter>
	<parameter name="orClauseCategoryFilter" />
	<parameter name="maxElemForItem">Contents for each page</parameter>
	<parameter name="maxElements">Number of contents</parameter>
	<parameter name="filters" />
	<parameter name="title_{lang}">Widget Title in lang {lang}</parameter>
	<parameter name="pageLink">The code of the Page to link</parameter>
	<parameter name="linkDescr_{lang}">Link description in lang {lang}</parameter>
	<action name="listViewerConfig"/>
</config>','jacms',NULL,NULL,1,NULL,NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('row_content_viewer_list','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Contents - Publish Contents</property>
<property key="it">Contenuti - Pubblica Contenuti</property>
</properties>','<config>
	<parameter name="contents">Contents to Publish (mandatory)</parameter>
	<parameter name="maxElemForItem">Contents for each page</parameter>
	<parameter name="title_{lang}">Widget Title in lang {lang}</parameter>
	<parameter name="pageLink">The code of the Page to link</parameter>
	<parameter name="linkDescr_{lang}">Link description in lang {lang}</parameter>
	<action name="rowListViewerConfig" />
</config>','jacms',NULL,NULL,1,'free',NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('main_banner_accordion','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Helping You Reach Financial Freedom - with accordion</property>
<property key="it">Helping You Reach Financial Freedom - with accordion</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('app-user-table-widget','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">App User Table Widget</property>
<property key="it">App User Table Widget</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free',NULL,'login-bundle');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('app-user-details-widget','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">App User Details Widget</property>
<property key="it">App User Details Widget</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free',NULL,'login-bundle');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('app-user-form-widget','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">App User Table Widget</property>
<property key="it">App User Table Widget</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free',NULL,'login-bundle');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('login-widget','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">App User Table Widget</property>
<property key="it">App User Table Widget</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free',NULL,'login-bundle');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('main_banner_inner','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Earn interest on your checking account - inner banner</property>
<property key="it">Earn interest on your checking account - inner banner</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('main_banner_darkgreen','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Financial Health Dashboard - dark green</property>
<property key="it">Financial Health Dashboard - dark green</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('main_banner_white','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Open a Seed Checking Account Today - white</property>
<property key="it">Open a Seed Checking Account Today - white</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('main_banner_green','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">We Got Your Back - light green</property>
<property key="it">We Got Your Back - light green</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('footer','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Footer</property>
<property key="it">Footer</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('Savings','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Savings</property>
<property key="it">Savings</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('Credit-card','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Credit Card</property>
<property key="it">Credit Card</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('Checking-Table','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Checking Table</property>
<property key="it">Checking Table</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('Notification-Bar','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Notification bar</property>
<property key="it">Notification bar</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('pay_bill','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Pay bills notification mock</property>
<property key="it">Pay bills notification mock</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('Brand-Logo','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Brand Logo</property>
<property key="it">Brand Logo</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('login','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Login</property>
<property key="it">Login</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('send_money','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Send money notification mock</property>
<property key="it">Send money notification mock</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('apply_form_banner','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Apply form banner</property>
<property key="it">Apply form banner</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free',NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('credit_card_user_apply_form','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Credit card user apply form</property>
<property key="it">Form di applicazione carta di credito</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('seed-user-form','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Seed User form</property>
<property key="it">Seed User form</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('applyform','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Apply form</property>
<property key="it">Apply form</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('dropdown_sign_in','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Dropdown Sign In</property>
<property key="it">Dropdown Sign In</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free',NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('investments','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Investments  notification mock</property>
<property key="it">Investments  notification mock</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('find-atm','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Find ATM</property>
<property key="it">Trova ATM</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('Checking','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Checking card dummy</property>
<property key="it">Checking card dummy</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('manage_users','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Manage Users</property>
<property key="it">Gestione Utenti</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free',NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('transfer_money','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Transfer Money  notification mock</property>
<property key="it">Transfer Money notification mock</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('signup','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Signup</property>
<property key="it">Registrazione</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free',NULL,NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('seedscard-transaction-table','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Seedscard Transaction Table</property>
<property key="it">Seedscard Transaction Table</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','','');
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('seeds_card','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Seeds Card</property>
<property key="it">Seeds Card</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','{"customElement":"seeds-card-config","resources":["widgets/seeds-card-config/static/js/runtime.js","widgets/seeds-card-config/static/js/vendor.js","widgets/seeds-card-config/static/js/main.js"]}',NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('list_item','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">List item</property>
<property key="it">Elemento lista</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free','{"customElement":"list-item-config","resources":["widgets/list-item/config/js/runtime.js","widgets/list-item/config/js/vendor.js","widgets/list-item/config/js/main.js"]}',NULL);
INSERT INTO widgetcatalog (code,titles,parameters,plugincode,parenttypecode,defaultconfig,locked,maingroup,configui,bundleid) VALUES ('Login_buttons','<?xml version="1.0" encoding="UTF-8"?>
<properties>
<property key="en">Seed Login buttons</property>
<property key="it">Seed Login buttons</property>
</properties>

',NULL,NULL,NULL,NULL,0,'free',NULL,NULL);
