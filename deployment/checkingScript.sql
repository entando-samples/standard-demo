-- Terminale db: https://lab.entando.org:8443/console/project/sales-demo/browse/pods/checking-plugin-db-deployment-6b6b6878d4-2jq85?tab=terminal 
-- psql
-- \c checking_plugin_db

-- CHECKING account checking
-- START ensure that the CHECKING sequence is increment by 1 -- 
DO $$
DECLARE
FIRST_INCREMENT INTEGER = (SELECT increment from information_schema.sequences);
BEGIN
IF FIRST_INCREMENT > 1 THEN ALTER SEQUENCE checking_plugin_plugindb.sequence_generator INCREMENT 1;
END IF;
END $$;
-- END ensure that the CHECKING sequence is increment by 1 -- 
-- START create account for user --
DO $$
DECLARE
USERID VARCHAR = '067c3e37-09f8-49e8-a904-c814b0a3ed1e';
ACCOUNT_NUMBER VARCHAR = '746374859866'; 
IDACCOUNT INTEGER = (SELECT id from checking_plugin_plugindb.checking where user_id = USERID);
INCREMENT INTEGER = (SELECT nextval('checking_plugin_plugindb.sequence_generator'));
BEGIN
IF IDACCOUNT IS NULL THEN insert into checking_plugin_plugindb.checking values (INCREMENT, ACCOUNT_NUMBER, 43354, USERID);
END IF;
END $$;
-- END create account for user --

SELECT id, balance from checking_plugin_plugindb.checking where user_id = '067c3e37-09f8-49e8-a904-c814b0a3ed1e';
-- id: 1152; balance: 43354.00 --

-- Terminale db: https://lab.entando.org:8443/console/project/sales-demo/browse/pods/checkingtransaction-plugin-db-deployment-5c4d5c6644-qfrmm?tab=terminal
-- psql
-- \c checkingtransaction_plugin_db

-- CHECKING TRANSACTIONS
-- START ensure that the CHECKING TRANSACTIONS sequence is increment by 1 -- 
DO $$
DECLARE
FIRST_INCREMENT INTEGER = (SELECT increment from information_schema.sequences);
BEGIN
IF FIRST_INCREMENT > 1 THEN ALTER SEQUENCE checkingtransaction_plugin_plugindb.sequence_generator INCREMENT 1;
END IF;
END $$;
-- END ensure that the CHECKING TRANSACTIONS sequence is increment by 1 -- 
-- START insert CHECKING TRANSACTIONS --
DO $$
DECLARE
IDACCOUNT INTEGER = 1152; -- cambiare IDACCOUNT secondo esigenze 
BEGIN

INSERT INTO checkingtransaction_plugin_plugindb.checkingtransaction VALUES
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-22', 'Third transaction', 23.72, 43354, 1152),
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-21', 'Computer', 32.98, 43321.02, 1152),
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-20', 'Car ensurance', 9.25, 43311.77, 1152),
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-19', 'Second transaction', 28.79, 43282.98, 1152),
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-18', 'research paper on Friday', 9.25, 43273.73, 1152),
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-17', 'research paper on Friday', 23.72, 43250.01, 1152),
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-16', 'Second transaction', 71.79, 43178.22, 1152),
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-15', 'Oven', 49.98, 43128.24, 1152),
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-14', 'Computer', 24.39, 43103.85, 1152),
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-13', 'Greetings from the galaxy', 94.77, 43009.08, 1152),
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-12', 'antenna tower', 71.79, 42937.29, 1152),
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-11', 'Repairs', 37.75, 42899.54, 1152),
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-10', 'Telephone expense', 413.95, 42485.59, 1152),
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-09', 'Car ensurance', 71.79, 42413.8, 1152),
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-08', 'First transaction', 58.5, 42355.3, 1152),
((SELECT nextval('checkingtransaction_plugin_plugindb.sequence_generator')), '2020-04-07', 'Third transaction', 9.25, 42346.05, 1152);
END $$;
-- END insert CHECKING TRANSACTIONS -- 