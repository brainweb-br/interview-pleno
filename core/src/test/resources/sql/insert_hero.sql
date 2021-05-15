insert into power_stats values(uuid_generate_v4(), 1,1,1,1,now(), now());
insert into hero values(uuid_generate_v4(), 'teste', 'HUMAN', (select id from power_stats limit 1), true, now(), now());