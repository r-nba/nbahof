ALTER TABLE TEAM
  ADD COLUMN TLA VARCHAR(3) NULL;


UPDATE TEAM SET TLA='SAC' WHERE team_name = 'Sacramento Kings';
UPDATE TEAM SET TLA='PHO' WHERE team_name = 'Phoenix Suns';
UPDATE TEAM SET TLA='LAL' WHERE team_name = 'Los Angeles Lakers';
UPDATE TEAM SET TLA='DAL' WHERE team_name = 'Dallas Mavericks';
UPDATE TEAM SET TLA='MEM' WHERE team_name = 'Memphis Grizzlies';
UPDATE TEAM SET TLA='NOP' WHERE team_name = 'New Orleans Pelicans';
UPDATE TEAM SET TLA='UTA' WHERE team_name = 'Utah Jazz';
UPDATE TEAM SET TLA='POR' WHERE team_name = 'Portland Trail Blazers';
UPDATE TEAM SET TLA='LAC' WHERE team_name = 'LA Clippers';
UPDATE TEAM SET TLA='DEN' WHERE team_name = 'Denver Nuggets';
UPDATE TEAM SET TLA='MIN' WHERE team_name = 'Minnesota Timberwolves';
UPDATE TEAM SET TLA='OKC' WHERE team_name = 'Oklahoma City Thunder';
UPDATE TEAM SET TLA='SAS' WHERE team_name = 'San Antonio Spurs';
UPDATE TEAM SET TLA='HOU' WHERE team_name = 'Houston Rockets';
UPDATE TEAM SET TLA='GSW' WHERE team_name = 'Golden State Warriors';
UPDATE TEAM SET TLA='CHI' WHERE team_name = 'Chicago Bulls';
UPDATE TEAM SET TLA='ATL' WHERE team_name = 'Atlanta Hawks';
UPDATE TEAM SET TLA='BKN' WHERE team_name = 'Brooklyn Nets';
UPDATE TEAM SET TLA='NYK' WHERE team_name = 'New York Knicks';
UPDATE TEAM SET TLA='IND' WHERE team_name = 'Indiana Pacers';
UPDATE TEAM SET TLA='ORL' WHERE team_name = 'Orlando Magic';
UPDATE TEAM SET TLA='DET' WHERE team_name = 'Detroit Pistons';
UPDATE TEAM SET TLA='PHI' WHERE team_name = 'Philadelphia 76ers';
UPDATE TEAM SET TLA='CHA' WHERE team_name = 'Charlotte Hornets';
UPDATE TEAM SET TLA='MIA' WHERE team_name = 'Miami Heat';
UPDATE TEAM SET TLA='MIL' WHERE team_name = 'Milwaukee Bucks';
UPDATE TEAM SET TLA='WAS' WHERE team_name = 'Washington Wizards';
UPDATE TEAM SET TLA='TOR' WHERE team_name = 'Toronto Raptors';
UPDATE TEAM SET TLA='CLE' WHERE team_name = 'Cleveland Cavaliers';
UPDATE TEAM SET TLA='BOS' WHERE team_name = 'Boston Celtics';