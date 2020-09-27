-- Terminale db: https://lab.entando.org:8443/console/project/sales-demo/browse/pods/savings-plugin-db-deployment-c89fc84b6-ml2sb?tab=terminal
-- psql
-- \c savings_plugin_db

-- SAVINGS account savings
-- START ensure that the SAVINGS sequence is increment by 1 -- 
DO $$
DECLARE
FIRST_INCREMENT INTEGER = (SELECT increment from information_schema.sequences);
BEGIN
IF FIRST_INCREMENT > 1 THEN ALTER SEQUENCE savings_plugin_plugindb.sequence_generator INCREMENT 1;
END IF;
END $$;
-- END ensure that the SAVINGS sequence is increment by 1 -- 
-- START create account for user --
DO $$
DECLARE
USERID VARCHAR = '067c3e37-09f8-49e8-a904-c814b0a3ed1e';
ACCOUNT_NUMBER VARCHAR = '746374859866'; 
IDACCOUNT INTEGER = (SELECT id from savings_plugin_plugindb.savings where user_id = USERID);
INCREMENT INTEGER = (SELECT nextval('savings_plugin_plugindb.sequence_generator'));
BEGIN
IF IDACCOUNT IS NULL THEN insert into savings_plugin_plugindb.savings values (INCREMENT, ACCOUNT_NUMBER, 43354, USERID);
END IF;
END $$;
-- END create account for user --

SELECT id, balance from savings_plugin_plugindb.savings where user_id = '067c3e37-09f8-49e8-a904-c814b0a3ed1e';
-- id: 1050; balance: 43354.00 --

-- Terminale db: https://lab.entando.org:8443/console/project/sales-demo/browse/pods/savingstransaction-plugin-db-deployment-7f9bf9bc88-bx477?tab=terminal
-- psql
-- \c savingstransaction_plugin_db

-- SAVINGS TRANSACTIONS
-- START ensure that the SAVINGS TRANSACTIONS sequence is increment by 1 -- 
DO $$
DECLARE
FIRST_INCREMENT INTEGER = (SELECT increment from information_schema.sequences);
BEGIN
IF FIRST_INCREMENT > 1 THEN ALTER SEQUENCE savingstransaction_plugin_plugindb.sequence_generator INCREMENT 1;
END IF;
END $$;
-- END ensure that the SAVINGS TRANSACTIONS sequence is increment by 1 -- 
-- START insert SAVINGS TRANSACTIONS --
DO $$
DECLARE
IDACCOUNT INTEGER = 1050; -- cambiare IDACCOUNT secondo esigenze 
BEGIN
INSERT INTO savingstransaction_plugin_plugindb.savingstransaction VALUES 
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-22', 'antenna tower', 60.63, 43354, 1050),
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-21', 'research paper on Friday', 37.75, 43316.25, 1050),
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-20', 'Computer', 28.79, 43287.46, 1050),
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-19', 'Repairs', 413.95, 42873.51, 1050),
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-18', 'Repairs', 19.17, 42854.34, 1050),
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-17', 'toasted cheese and tuna sandwiches', 48.84, 42805.5, 1050),
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-16', 'road work ahead', 28.79, 42776.71, 1050),
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-15', 'Repairs', 32.98, 42743.73, 1050),
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-14', 'Second transaction', 23.72, 42720.01, 1050),
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-13', 'Greetings from the galaxy', 9.25, 42710.76, 1050),
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-12', 'Second transaction', 71.79, 42638.97, 1050),
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-11', 'Computer', 32.98, 42605.99, 1050),
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-10', 'Car ensurance', 3.5, 42602.49, 1050),
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-09', 'Second transaction', 9.25, 42593.24, 1050),
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-08', 'toasted cheese and tuna sandwiches', 413.95, 42179.29, 1050),
((SELECT nextval('savingstransaction_plugin_plugindb.sequence_generator')), '2020-04-07', 'Telephone expense', 24.39, 42154.9, 1050);
END $$;
-- END insert SAVINGS TRANSACTIONS -- 
