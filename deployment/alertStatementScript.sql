-- Terminale db: https://lab.entando.org:8443/console/project/sales-demo/browse/pods/seedsdashboard-plugin-db-deployment-58d8d64d4-nlq6d?tab=terminal
-- psql
-- \c seedsdashboard_plugin_db

-- START ensure that the SEEDSDASHBOARD sequence is increment by 1 -- 
DO $$
DECLARE
FIRST_INCREMENT INTEGER = (SELECT increment from information_schema.sequences);
BEGIN
IF FIRST_INCREMENT > 1 THEN ALTER SEQUENCE seedsdashboard_plugin_plugindb.sequence_generator INCREMENT 1;
END IF;
END $$;
-- END ensure that the SEEDSDASHBOARD sequence is increment by 1 -- 

-- START insert ALERTS --
DO $$
DECLARE
USERID VARCHAR = '067c3e37-09f8-49e8-a904-c814b0a3ed1e'; -- cambiare USERID secondo esigenze 
BEGIN

INSERT INTO seedsdashboard_plugin_plugindb.alert VALUES
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Third transaction', '2020-04-22', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Computer', '2020-04-21', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Car ensurance', '2020-04-20', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Second transaction', '2020-04-19', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'research paper on Friday', '2020-04-18', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'research paper on Friday', '2020-04-17', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Second transaction', '2020-04-16', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Oven', '2020-04-15', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Computer', '2020-04-14', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Greetings from the galaxy', '2020-04-13', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'antenna tower', '2020-04-12', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Repairs', '2020-04-11', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Telephone expense', '2020-04-10', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Car ensurance', '2020-04-09', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'First transaction', '2020-04-08', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Third transaction', '2020-04-07', false, USERID);
END $$;
-- END insert ALERT -- 

-- START reset notification to UNREAD --
DO $$
DECLARE
USERID VARCHAR = '067c3e37-09f8-49e8-a904-c814b0a3ed1e'; -- cambiare USERID secondo esigenze 
BEGIN
UPDATE seedsdashboard_plugin_plugindb.alert SET read = false where user_id = USERID;
END $$;
-- END reset notification to UNREAD --

-- START insert STATEMENT --
DO $$
DECLARE
USERID VARCHAR = '067c3e37-09f8-49e8-a904-c814b0a3ed1e'; -- cambiare USERID secondo esigenze 
BEGIN

INSERT INTO seedsdashboard_plugin_plugindb.statement VALUES
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Third transaction', '2020-04-22', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Computer', '2020-04-21', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Car ensurance', '2020-04-20', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Second transaction', '2020-04-19', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'research paper on Friday', '2020-04-18', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'research paper on Friday', '2020-04-17', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Second transaction', '2020-04-16', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Oven', '2020-04-15', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Computer', '2020-04-14', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Greetings from the galaxy', '2020-04-13', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'antenna tower', '2020-04-12', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Repairs', '2020-04-11', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Telephone expense', '2020-04-10', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Car ensurance', '2020-04-09', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'First transaction', '2020-04-08', false, USERID),
((SELECT nextval('seedsdashboard_plugin_plugindb.sequence_generator')), 'Third transaction', '2020-04-07', false, USERID);
END $$;
-- END insert STATEMENT -- 

-- START reset notification to UNREAD --
DO $$
DECLARE
USERID VARCHAR = '067c3e37-09f8-49e8-a904-c814b0a3ed1e'; -- cambiare USERID secondo esigenze 
BEGIN
UPDATE seedsdashboard_plugin_plugindb.statement SET read = false where user_id = USERID;
END $$;
-- END reset notification to UNREAD --