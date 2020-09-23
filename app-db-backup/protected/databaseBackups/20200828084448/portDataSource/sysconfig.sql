INSERT INTO sysconfig (version,item,descr,config) VALUES ('production','langs','Definition of the system languages','<?xml version="1.0" encoding="UTF-8"?>
<Langs>
	<Lang>
		<code>it</code>
		<descr>Italiano</descr>
	</Lang>
	<Lang>
		<code>en</code>
		<descr>English</descr>
		<default>true</default>
	</Lang>
</Langs>
');
INSERT INTO sysconfig (version,item,descr,config) VALUES ('production','userProfileTypes','User Profile Types Definitions','<?xml version="1.0" encoding="UTF-8"?>
<profiletypes>
	<profiletype typecode="PFL" typedescr="Default user profile">
		<attributes>
			<attribute name="fullname" attributetype="Monotext" description="Full Name" searchable="true">
				<validations>
					<required>true</required>
				</validations>
				<roles>
					<role>userprofile:fullname</role>
				</roles>
			</attribute>
			<attribute name="email" attributetype="Monotext" description="Email" searchable="true">
				<validations>
					<required>true</required>
					<regexp><![CDATA[.+@.+.[a-z]+]]></regexp>
				</validations>
				<roles>
					<role>userprofile:email</role>
				</roles>
			</attribute>
		</attributes>
	</profiletype>
</profiletypes>');
INSERT INTO sysconfig (version,item,descr,config) VALUES ('production','dataTypeDefinitions','Definition of the Type Types','<datatypes />');
INSERT INTO sysconfig (version,item,descr,config) VALUES ('production','dataobjectsubdir','Name of the sub-directory containing dataobject indexing files','index');
INSERT INTO sysconfig (version,item,descr,config) VALUES ('production','imageDimensions','Definition of the resized image dimensions','<Dimensions>
	<Dimension>
		<id>1</id>
		<dimx>90</dimx>
		<dimy>90</dimy>
	</Dimension>
	<Dimension>
		<id>2</id>
		<dimx>130</dimx>
		<dimy>130</dimy>
	</Dimension>
	<Dimension>
		<id>3</id>
		<dimx>150</dimx>
		<dimy>150</dimy>
	</Dimension>
</Dimensions>');
INSERT INTO sysconfig (version,item,descr,config) VALUES ('production','jacms_resourceMetadataMapping','Mapping between resource Metadata and resource attribute fields','<mapping>
    <field key="alt"></field>
    <field key="description"></field>
    <field key="legend"></field>
    <field key="title"></field>
</mapping>');
INSERT INTO sysconfig (version,item,descr,config) VALUES ('production','jpkiebpm_config','PAM service configuration','<?xml version="1.0" encoding="UTF-8"?>
<kiaBpmConfigFactory>
   <kieBpmConfigeMap>
      <entry>
         <key>1</key>
         <value>
            <active>true</active>
            <id>1</id>
            <name>default</name>
            <username>krisv</username>
            <password>jvK4iusIxd7iqfDVu/CuvC5Q6gfR1cTS9wexnoVwk+aGKsLS6asGWA==</password>
            <hostname>localhost</hostname>
            <schema>http</schema>
            <port>8080</port>
            <timeout>1000</timeout>
            <webapp>kie-server</webapp>
         </value>
      </entry>
   </kieBpmConfigeMap>
</kiaBpmConfigFactory>');
INSERT INTO sysconfig (version,item,descr,config) VALUES ('production','entandoComponentsReport','The component installation report','<?xml version="1.0" encoding="UTF-8"?>
<reports status="OK">
	<creation>2020-04-30 14:10:09</creation>
	<lastupdate>2020-04-30 14:10:11</lastupdate>
	<components>
		<component code="entandoCore" date="2020-04-30 14:10:09" status="OK">
			<schema status="OK">
				<datasource name="portDataSource" status="OK">
					<table name="sysconfig" />
					<table name="categories" />
					<table name="localstrings" />
					<table name="pagemodels" />
					<table name="pages" />
					<table name="pages_metadata_online" />
					<table name="pages_metadata_draft" />
					<table name="widgetcatalog" />
					<table name="guifragment" />
					<table name="widgetconfig" />
					<table name="widgetconfig_draft" />
					<table name="uniquekeys" />
				</datasource>
				<datasource name="servDataSource" status="OK">
					<table name="authgroups" />
					<table name="authpermissions" />
					<table name="authroles" />
					<table name="authrolepermissions" />
					<table name="authusers" />
					<table name="authusergrouprole" />
					<table name="api_oauth_consumers" />
					<table name="api_oauth_tokens" />
					<table name="apicatalog_methods" />
					<table name="apicatalog_services" />
					<table name="authuserprofiles" />
					<table name="authuserprofilesearch" />
					<table name="authuserprofileattrroles" />
					<table name="actionlogrecords" />
					<table name="actionlogrelations" />
					<table name="actionloglikerecords" />
					<table name="actionlogcommentrecords" />
					<table name="dataobjectmodels" />
					<table name="dataobjects" />
					<table name="dataobjectrelations" />
					<table name="dataobjectsearch" />
					<table name="dataobjectattributeroles" />
				</datasource>
			</schema>
			<data status="OK">
				<datasource name="portDataSource" status="RESTORE" />
				<datasource name="servDataSource" status="RESTORE" />
			</data>
			<postProcess status="NOT_AVAILABLE" />
		</component>
		<component code="jacms" date="2020-04-30 14:10:10" status="OK">
			<schema status="OK">
				<datasource name="portDataSource" status="OK">
					<table name="contentmodels" />
					<table name="contents" />
					<table name="resources" />
					<table name="resourcerelations" />
					<table name="contentrelations" />
					<table name="contentsearch" />
					<table name="contentattributeroles" />
					<table name="workcontentrelations" />
					<table name="workcontentsearch" />
					<table name="workcontentattributeroles" />
				</datasource>
				<datasource name="servDataSource" status="NOT_AVAILABLE" />
			</schema>
			<data status="OK">
				<datasource name="portDataSource" status="RESTORE" />
				<datasource name="servDataSource" status="RESTORE" />
			</data>
			<postProcess status="NOT_AVAILABLE" />
		</component>
		<component code="entando-admin-console" date="2020-04-30 14:10:11" status="OK">
			<schema status="OK">
				<datasource name="portDataSource" status="NOT_AVAILABLE" />
				<datasource name="servDataSource" status="OK">
					<table name="authusershortcuts" />
				</datasource>
			</schema>
			<data status="OK">
				<datasource name="portDataSource" status="RESTORE" />
				<datasource name="servDataSource" status="RESTORE" />
			</data>
			<postProcess status="NOT_AVAILABLE" />
		</component>
		<component code="entando-portal-ui" date="2020-04-30 14:10:11" status="OK">
			<schema status="OK">
				<datasource name="portDataSource" status="NOT_AVAILABLE" />
				<datasource name="servDataSource" status="NOT_AVAILABLE" />
			</schema>
			<data status="OK">
				<datasource name="portDataSource" status="RESTORE" />
				<datasource name="servDataSource" status="RESTORE" />
			</data>
			<postProcess status="NOT_AVAILABLE" />
		</component>
		<component code="entando-app-view-cms-default" date="2020-04-30 14:10:11" status="OK">
			<schema status="OK">
				<datasource name="portDataSource" status="NOT_AVAILABLE" />
				<datasource name="servDataSource" status="NOT_AVAILABLE" />
			</schema>
			<data status="OK">
				<datasource name="portDataSource" status="RESTORE" />
				<datasource name="servDataSource" status="RESTORE" />
			</data>
			<postProcess status="NOT_AVAILABLE" />
		</component>
	</components>
</reports>

');
INSERT INTO sysconfig (version,item,descr,config) VALUES ('production','params','Configuration params.','<?xml version="1.0" encoding="UTF-8"?>
<Params>
	<Param name="urlStyle">classic</Param>
	<Param name="hypertextEditor">fckeditor</Param>
	<Param name="treeStyle_page">classic</Param>
	<Param name="treeStyle_category">classic</Param>
	<Param name="startLangFromBrowser">false</Param>
	<Param name="firstTimeMessages">false</Param>
	<Param name="baseUrl">request</Param>
	<Param name="baseUrlContext">true</Param>
	<Param name="useJsessionId">false</Param>
	<Param name="gravatarIntegrationEnabled">false</Param>
	<Param name="editEmptyFragmentEnabled">false</Param>
	<Param name="argon2">true</Param>
	<SpecialPages>
		<Param name="notFoundPageCode">notfound</Param>
		<Param name="homePageCode">homepage</Param>
		<Param name="errorPageCode">errorpage</Param>
		<Param name="loginPageCode">login</Param>
	</SpecialPages>
	<FeaturesOnDemand>
		<Param name="groupsOnDemand">true</Param>
		<Param name="categoriesOnDemand">true</Param>
		<Param name="contentTypesOnDemand">true</Param>
		<Param name="contentModelsOnDemand">true</Param>
		<Param name="apisOnDemand">true</Param>
		<Param name="resourceArchivesOnDemand">true</Param>
	</FeaturesOnDemand>
	<ExtendendPrivacyModule>
		<Param name="extendedPrivacyModuleEnabled">false</Param>
		<Param name="maxMonthsSinceLastAccess">6</Param>
		<Param name="maxMonthsSinceLastPasswordChange">3</Param>
	</ExtendendPrivacyModule>
	<ExtraParams>
		<Param name="legacyPasswordsUpdated">true</Param>
		<Param name="page_preview_hash">zITkxlqOoqWetiCt4P51</Param>
	</ExtraParams>
</Params>

');
INSERT INTO sysconfig (version,item,descr,config) VALUES ('production','subIndexDir','Name of the sub-directory containing content indexing files','indexdir20200827102918');
INSERT INTO sysconfig (version,item,descr,config) VALUES ('production','contentTypes','Definition of the Content Types','<?xml version="1.0" encoding="UTF-8"?>
<contenttypes>
	<contenttype typecode="BNR" typedescr="Banners" viewpage="**NULL**" listmodel="**NULL**" defaultmodel="**NULL**">
		<attributes>
			<attribute name="title" attributetype="Text" description="Titolo" indexingtype="TEXT">
				<validations>
					<required>true</required>
				</validations>
			</attribute>
			<attribute name="subtitle" attributetype="Hypertext" description="Sottotitolo">
				<validations>
					<required>true</required>
				</validations>
			</attribute>
			<attribute name="descr" attributetype="Hypertext" description="Description" />
			<attribute name="img" attributetype="Image" description="Lateral Image" />
			<attribute name="link" attributetype="Link" description="CTA Link" />
			<list name="accord" attributetype="Monolist" description="Accordion or Card list">
				<nestedtype>
					<attribute name="accord" attributetype="Composite">
						<attributes>
							<attribute name="idcard" attributetype="Number" description="Card number">
								<validations>
									<required>true</required>
								</validations>
							</attribute>
							<attribute name="headtitle" attributetype="Text" description="Header title">
								<validations>
									<required>true</required>
								</validations>
							</attribute>
							<attribute name="atitle" attributetype="Text" description="Accordion item title">
								<validations>
									<required>true</required>
								</validations>
							</attribute>
							<attribute name="asubtitle" attributetype="Hypertext" description="Accordion item description" />
							<attribute name="aimg" attributetype="Image" description="Logo on card" />
							<attribute name="open" attributetype="Enumerator" description="Open accordion" separator=","><![CDATA[false,true]]></attribute>
						</attributes>
					</attribute>
				</nestedtype>
			</list>
		</attributes>
	</contenttype>
	<contenttype typecode="MPC" typedescr="Multi-Purpose  Content" viewpage="**NULL**" listmodel="**NULL**" defaultmodel="**NULL**">
		<attributes>
			<attribute name="title" attributetype="Hypertext" description="title" />
			<attribute name="subtitle" attributetype="Hypertext" description="subtitle" />
			<attribute name="body" attributetype="Hypertext" description="content body" />
			<attribute name="image" attributetype="Image" description="image" />
			<list name="links" attributetype="Monolist" description="list of links">
				<nestedtype>
					<attribute name="links" attributetype="Link" />
				</nestedtype>
			</list>
		</attributes>
	</contenttype>
	<contenttype typecode="NWS" typedescr="News" viewpage="**NULL**" listmodel="**NULL**" defaultmodel="**NULL**">
		<attributes>
			<attribute name="title" attributetype="Text" description="title" searchable="true" indexingtype="TEXT">
				<validations>
					<required>true</required>
				</validations>
			</attribute>
			<attribute name="subtitle" attributetype="Hypertext" description="subtitle">
				<validations>
					<required>true</required>
				</validations>
			</attribute>
			<attribute name="body" attributetype="Hypertext" description="content body">
				<validations>
					<required>true</required>
				</validations>
			</attribute>
			<attribute name="img" attributetype="Image" description="image" />
			<list name="links" attributetype="Monolist" description="list of links">
				<nestedtype>
					<attribute name="links" attributetype="Link" />
				</nestedtype>
			</list>
			<list name="attaches" attributetype="Monolist" description="list of attaches">
				<nestedtype>
					<attribute name="attaches" attributetype="Attach" />
				</nestedtype>
			</list>
		</attributes>
	</contenttype>
	<contenttype typecode="PRD" typedescr="Products" viewpage="**NULL**" listmodel="**NULL**" defaultmodel="**NULL**">
		<attributes>
			<attribute name="title" attributetype="Text" description="title" searchable="true" indexingtype="TEXT">
				<validations>
					<required>true</required>
				</validations>
			</attribute>
			<attribute name="subtitle" attributetype="Hypertext" description="subtitle">
				<validations />
			</attribute>
			<list name="paragraphs" attributetype="Monolist" description="paragraphs list">
				<validations />
				<nestedtype>
					<attribute name="paragraphs" attributetype="Composite">
						<attributes>
							<attribute name="mtitle" attributetype="Text" description="title">
								<validations />
							</attribute>
							<attribute name="mbody" attributetype="Hypertext" description="content body">
								<validations />
							</attribute>
							<attribute name="mimage" attributetype="Image" description="image">
								<validations />
							</attribute>
						</attributes>
					</attribute>
				</nestedtype>
			</list>
			<attribute name="image" attributetype="Image" description="image">
				<validations />
			</attribute>
			<list name="links" attributetype="Monolist" description="list of links">
				<validations />
				<nestedtype>
					<attribute name="links" attributetype="Link">
						<validations />
					</attribute>
				</nestedtype>
			</list>
			<list name="attaches" attributetype="Monolist" description="list of attaches">
				<validations />
				<nestedtype>
					<attribute name="attaches" attributetype="Attach">
						<validations />
					</attribute>
				</nestedtype>
			</list>
		</attributes>
	</contenttype>
</contenttypes>

');
