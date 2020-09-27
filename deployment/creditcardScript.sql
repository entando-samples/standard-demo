-- Terminale db: https://lab.entando.org:8443/console/project/sales-demo/browse/pods/creditcard-plugin-db-deployment-9799775fb-6wfc2?tab=terminal
-- psql
-- \c creditcard_plugin_db

-- CREDITCARD account creditcard
-- START ensure that the CREDITCARD sequence is increment by 1 -- 
DO $$
DECLARE
FIRST_INCREMENT INTEGER = (SELECT increment from information_schema.sequences);
BEGIN
IF FIRST_INCREMENT > 1 THEN ALTER SEQUENCE creditcard_plugin_plugindb.sequence_generator INCREMENT 1;
END IF;
END $$;
-- END ensure that the CREDITCARD sequence is increment by 1 -- 
-- START create account for user --
DO $$
DECLARE
USERID VARCHAR = '067c3e37-09f8-49e8-a904-c814b0a3ed1e';
ACCOUNT_NUMBER VARCHAR = '746374859866'; 
IDACCOUNT INTEGER = (SELECT id from creditcard_plugin_plugindb.creditcard where user_id = USERID);
INCREMENT INTEGER = (SELECT nextval('creditcard_plugin_plugindb.sequence_generator'));
BEGIN
IF IDACCOUNT IS NULL THEN insert into creditcard_plugin_plugindb.creditcard values (INCREMENT, ACCOUNT_NUMBER, 43354, 7566, USERID);
END IF;
END $$;
-- END create account for user --

SELECT id, balance from creditcard_plugin_plugindb.creditcard where user_id = '067c3e37-09f8-49e8-a904-c814b0a3ed1e';
-- id: 1051; balance: 43354.00 --

-- Terminale db: https://lab.entando.org:8443/console/project/sales-demo/browse/pods/creditcardtransaction-plugin-db-deployment-7587497558-gw8t6?tab=terminal
-- psql
-- \c creditcardtransaction_plugin_db

-- CREDITCARD TRANSACTIONS
-- START ensure that the CREDITCARD TRANSACTIONS sequence is increment by 1 -- 
DO $$
DECLARE
FIRST_INCREMENT INTEGER = (SELECT increment from information_schema.sequences);
BEGIN
IF FIRST_INCREMENT > 1 THEN ALTER SEQUENCE creditcardtransaction_plugin_plugindb.sequence_generator INCREMENT 1;
END IF;
END $$;
-- END ensure that the CREDITCARD TRANSACTIONS sequence is increment by 1 -- 
-- START insert CREDITCARD TRANSACTIONS --
DO $$
DECLARE
IDACCOUNT INTEGER = 1051; -- cambiare IDACCOUNT secondo esigenze 
BEGIN
INSERT INTO creditcardtransaction_plugin_plugindb.creditcardtransaction VALUES 
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-22', 'Cake mix expense', 32.98, 43354, 1051),
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-21', 'antenna tower', 23.72, 43330.28, 1051),
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-20', 'Oven', 49.98, 43280.3, 1051),
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-19', 'Chocolate covered crickets', 58.5, 43221.8, 1051),
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-18', 'Telephone expense', 48.84, 43172.96, 1051),
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-17', 'Second transaction', 58.5, 43114.46, 1051),
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-16', 'antenna tower', 32.98, 43081.48, 1051),
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-15', 'Telephone expense', 94.77, 42986.71, 1051),
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-14', 'research paper on Friday', 48.84, 42937.87, 1051),
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-13', 'Third transaction', 49.98, 42887.89, 1051),
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-12', 'Greetings from the galaxy', 49.98, 42837.91, 1051),
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-11', 'Third transaction', 58.5, 42779.41, 1051),
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-10', 'Car ensurance', 28.79, 42750.62, 1051),
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-09', 'Oven', 3.5, 42747.12, 1051),
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-08', 'Telephone expense', 9.25, 42737.87, 1051),
((SELECT nextval('creditcardtransaction_plugin_plugindb.sequence_generator')), '2020-04-07', 'Oven', 94.77, 42643.1, 1051);
END $$;
-- END insert CREDITCARD TRANSACTIONS -- 
