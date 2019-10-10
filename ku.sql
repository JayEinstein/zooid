select * from str_after sa, str_library sl where sa.sl_id = sl.str and sl.str = '?'

SELECT * FROM str_after sa WHERE sa.sl_id = '?' and sa.slsa_id = '?'
select sl_id from str_library where str = '?'
select sl_id from str_library where str = '?'

# 查找 sa 主键
SELECT sa.sa_id FROM str_after sa 
WHERE
	sa.sl_id = ( SELECT sl_id FROM str_library WHERE str = '?' ) 
	AND sa.slsa_id = ( SELECT sl_id FROM str_library WHERE str = '?' );

# 
INSERT INTO str_after () VALUES()


select * from user where name='admin'