MYSQL

1. 数据库就是放置数据的地方，MYSQL 数据库的一种

2. MYSQL开始配置   [MySQL5.7.19安装](https://typora-1257023524.cos.ap-shanghai.myqcloud.com/MySQL5.7.19%E5%AE%89%E8%A3%85.docx)

   1.  MYSQL的安装和配置（配置 环境变量 的目的是 在任一一个目录去操作mysql服务）
   2.  MYSQL 需要管理员的权限操作，所以path 需要安装的管理员的目录下
   3.  启动mysql和停止mysql
       1.  net start mysql
       2.  net stop mysql

3. 连接MYSQL 服务

   mysql -h 主机ip -P 端口 -u 用户名 -p密码

   1. -p密码不要有空格
   2. -p后面没有写密码，回车会要求输入密码
   3. 如果没有写-h 主机，默认是本机
   4. 如果没有写-P 端口，默认是3306
   5. 实际工作中，3306 一般修改

4. MySQL管理软件

   1. navicat 图形化的MySQL管理软件

   2. SQLyog 图形化的MySQL管理软件

      1. 可以放大放小

5. MySQL的三层机构-破除神秘

   ![image-20221120233416343](https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221120233416343.png)

   DML（Data Manipulation Language）是数据操纵语言的缩写，指的是一类操纵数据的 SQL 语句。主要包括 `SELECT`、`INSERT`、`UPDATE`、`DELETE` 等语句。

   `SELECT` 语句用于查询数据库中的数据。例如：

   ```mysql
   SELECT * FROM accounts;
   ```

   `INSERT` 语句用于插入新的数据到表中。例如：

   ```mysql
   INSERT INTO accounts (name, balance) VALUES ('Alice', 1000);
   ```

   `UPDATE` 语句用于更新表中已有的数据。例如：

   ```mysql
   UPDATE accounts SET balance = balance + 100 WHERE name = 'Alice';
   ```

   `DELETE` 语句用于删除表中的数据。例如：

   ```mysql
   DELETE FROM accounts WHERE name = 'Alice';
   ```

   这些 DML 语句是 MySQL 数据库中最常用的语句，它们用于查询、插入、更新和删除数据库中的数据。

   

6. 创建数据库 和删除数据库

   1. create database [if not exists] db_name

      character set  指定数据库字符集，默认utf8, 

      collate 指定数据库字符集的校对规则，默认utf8_general_ci(不区分大小写)，如果是utf8_bin(区分大小写)

      ```mysql
      # 演示数据库的操作
      #创建一个名称为hsp_db01的数据库。[图形化和指令 演示]
      
      #使用指令创建数据库
      CREATE DATABASE hsp_db01;
      #删除数据库指令
      DROP DATABASE hsp_db01
      #创建一个使用utf8字符集的hsp_db02数据库
      CREATE DATABASE hsp_db02 CHARACTER SET utf8
      #创建一个使用utf8字符集，并带校对规则的hsp_db03数据库
      CREATE DATABASE hsp_db03 CHARACTER SET utf8 COLLATE utf8_bin
      #校对规则 utf8_bin 区分大小 默认utf8_general_ci 不区分大小写
      
      #下面是一条查询的sql , select 查询 * 表示所有字段 FROM 从哪个表
      #WHERE 从哪个字段 NAME = 'tom' 查询名字是tom
      SELECT *  
      	FROM t1 
      	WHERE NAME = 'tom'
      
      ```

      


   2. drop database db_name    删除数据库

   3. select * from t1 where `name` = 'tom'

      这是一条查询sql语句，select 查询 * 所有字段 from 从哪个表，where 从哪个字段，`name` = 'tom' 查询名字是tom

4. 查看、删除数据库

   ```mysql
   显示数据库所有
   show databases
   显示数据库创建语句
   show creat database db_name
   数据库删除语句
   drop database [if exists] db_name
   
   
   ```

5. 备份数据库(在DOS下执行)

   ```mysql
   mysqldump -u 用户名 -p -B 数据库1 数据库2 数据库n > d:\\文件名.sql
   ```

   恢复数据库(进入MySQL命令行再执行)

   ```mysql
   source d:\\文件名.sql
   ```

6. 备份数据库的表

   ```mysql
   mysqldump -u 用户名 -p 数据库 表1 表2 表n > d:\\文件名.sql
   ```

7. 创建表

   ```mysql
   create table table_name(
   	field1 datatype,
   	field2 datatype,
   	field3 datatype,
   ) character set collate utf8 engine innodb
   
   
   character set:字符集  collate 校对规则 engine 引擎
   field 列名 datatype 列类型/字段类型
   
   ```

   

Mysql常用数据类型(列类型)

![image-20221121220303518](https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221121220303518.png)



数值型：

```mysql
CREATE TABLE T05(
	id int  -- 这里的数值添加是按字节来算的，int是4个字节，所以有符号位是 负2^31 到 2^31-1
    -- 无符号(unsigned) 2^32 - 1
);
insert into t05 values(2147483647)   -- 2147483647 是 有符号 2^31 - 1 来的
```

文本型：

```mysql
CREATE TABLE T06(
	`name` char(255)   -- 这里的数值添加是按字符来算的，最大255字符，
);
insert into t05 values()   
```



```mysql
CREATE TABLE T06(
	`name` varchar(21844)   -- 这里的数值添加是按字符来算的，varchar他的大小是65535字节，还要减掉1-3个字节用来存储显示，所以如果是utf8 的话可以最大添加21844个字符
);
insert into t06 values()   
```

日期类型的基本使用

```mysql
-- date,datetime,timestamp
create table t14(
	birthday date, -- 生日
    job_time datetime -- 记录年月日 时分秒
    login_time timestamp -- 登入时间，如果希望login_time 自动更新，需要配置
   		 not null default current_timestamp
    	 on update current_time
);
insert into t14(birthday,job_time) values('2022-11-11','2022-11-11 10:10:10');
-- 这里 login_time  没有指定，更新后，会自动更新当前的时间。

```

例子：

```mysql
create table `emp`(
	id  int,
    `name` varchar(32),
    sex char(1),
    birthday date,
    entry_data datetime,
    job varchar(32),
    salary double,
    `resume` text
) character set utf8 collate utf8_bin engine innodb;

insert into `emp` values(100,'小妖怪','2000-11-11','2010-11-11','巡山的',3000,'大王叫我来巡山');
```



数据库表的删除：

```mysql

```

数据库表的修改：

```mysql
添加列：
alter table `table_name`
add column datatype,......;


修改列：
alter table `table_name`
modify column datatype,......
;

删除列：
alter table `table_name`
drop column ......
 ;
修改列名：
alter table `table_name`
`name` `user_name` varchar(32);

查看表的结构: desc 表名;
修改表名: rename table 表名 to 新表名
修改表字符集: alter table 表名 character set 字符集

```



<img src="https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221122222229973.png" alt="image-20221122222229973" style="zoom:50%;" />

```mysql
create table `emp`(
	id  int,
    `name` varchar(32),
    sex char(1),
    birthday date,
    entry_data datetime,
    job varchar(32),
    salary double,
    `resume` text
) character set utf8 collate utf8_bin engine innodb;

insert into `emp` values(100,'小妖怪','2000-11-11','2010-11-11','巡山的',3000,'大王叫我来巡山');

ALTER TABLE `emp` ADD image VARCHAR(255) NOT NULL DEFAULT '' AFTER `resume`;
ALTER TABLE `emp` MODIFY job VARCHAR(60) NOT NULL DEFAULT '';
ALTER TABLE `emp` DROP sex;
DESC `emp`;
RENAME TABLE `emp` TO `employee`;
ALTER TABLE `employee` CHARACTER SET utf8; 
ALTER TABLE `employee` CHANGE `name` `user_name` CHARACTER(32);

```



CRUD程序员：

insert

```mysql
INSERT INTO table_name (column ,....) values (values,......)

-- insert into 细节
-- 1.插入的数据应与字段的数据类型相同。
-- 2.数据的长度应在列的规定范围内，例如：不能将一个长度为80的字符串加入到长度为40的列中。
-- 3.在values中列出的数据位置必须与被加入的列的排列位置相对应。
-- 4.字符和日期类型数据应包含在单引号中。
-- 5.列可以插入空值[前提是该字段允许为空]，insert into table values(null)
-- 6.insert into tab_name(列名...) values (),(),() 形式添加多条记录
-- 7.如果是给表中的所有字段添加数据，可以不写前面的字段名称
-- 8.默认值的使用，当不给某个字段值时，如果有默认值就会添加，否则报错
```

update

```mysql
update table_name
	set column_expr1 where where_definition
-- 1.update语法可以用新值更新原有表行中的各列。
-- 2.set子句指示要修改哪些列和要给予哪些值。
-- 3.where字句指定应更新哪些行，如没有wehere字句，则更新所有的行。
-- 4.如果需要修改多个字段，可以通过 set字段1=值1，字段2=值2...
```

delete:

```mysql
delete from tab1_name
	[where where_definition]
	
-- 1.如果不使用where子句，将删除表中所有数据。
-- 2.DELETE语句不能删除某一列的值(可适用update 设为null或者'')。
update `employee` set job = '' where user_name = '老妖怪';
-- 3.使用DELETE语句仅删除记录，不删除表本身，如要删除表，使用drop table语句。drop table 表名；
```

select 基本语法

```mysql
select [distinct] *|{column1,column2,column3...}
	from table_name
	
-- 1.select 指定查询哪些列的数据
-- 2.column 指定列名
-- 3.*号代表查询所有列。
-- 4.from指定查询哪张表。
-- 5.distinct可选，指显示结果时候，是否去掉重复数据

CREATE TABLE student(
	id INT NOT NULL DEFAULT 1,
	NAME VARCHAR(20) NOT NULL DEFAULT '',
	chinese FLOAT NOT NULL DEFAULT 0.0,
	english FLOAT NOT NULL DEFAULT 0.0,
	math FLOAT NOT NULL DEFAULT 0.0
);

INSERT INTO student(id,NAME,chinese,english,math) VALUES(1,'韩顺平',89,78,90);
INSERT INTO student(id,NAME,chinese,english,math) VALUES(2,'张飞',67,98,56);
INSERT INTO student(id,NAME,chinese,english,math) VALUES(3,'宋江',87,78,77);
INSERT INTO student(id,NAME,chinese,english,math) VALUES(4,'关羽',88,98,90);
INSERT INTO student(id,NAME,chinese,english,math) VALUES(5,'赵云',82,84,67);
INSERT INTO student(id,NAME,chinese,english,math) VALUES(6,'欧阳锋',55,85,45);
INSERT INTO student(id,NAME,chinese,english,math) VALUES(7,'黄蓉',75,65,30);
INSERT INTO student(id,NAME,chinese,english,math) VALUES(8,'韩信',45,65,99);


-- 查询表中所有学生的信息。
SELECT * FROM student;
-- 查询表中所有学生的姓名和对应的英语成绩。
SELECT `name`,english FROM student;
-- 过滤表中重复数据 distinct 。
SELECT DISTINCT english FROM student;
-- 要查询的记录，每个字段都相同，才会去重
SELECT DISTINCT `name`, english FROM student;

```

select 使用表达式对查询的列进行运算

```mysql
select *|{column1 | expression, column2|expression,...}
	from table_name;

-- 统计每个学生的总分
SELECT `name`, (chinese+english+math) FROM student;
-- 在所有学生总分加10分的情况
SELECT `name`, (chinese + english + math + 10) FROM student;

```

select 在select语句中可使用as语句

```mysql
select column_name as 别名 from 表名;
-- 使用别名表示学生分数。
SELECT `name` AS '名字', (chinese + english + math + 10) AS total_score 
	FROM student;
```

select 在where子句中经常使用的运算符

<img src="https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221123223511770.png" alt="image-20221123223511770" style="zoom:50%;" />

```mysql
-- 使用where子句，进行过滤查询
-- 查询姓名为赵云的学生成绩
SELECT * FROM student WHERE `name` = '赵云';
-- 查询英语成绩大于90分的同学
SELECT * FROM student
	WHERE english > 90;
-- 查询总分大于200分的所有同学
SELECT * FROM student
	WHERE (english + chinese + math) > 200;	
-- 查询math大于60 并且(and) id大于4的学生成绩
SELECT * FROM student
	WHERE math > 60 AND id > 4;	
-- 查询英语成绩大于语文成绩的同学
SELECT * FROM student
	WHERE english > chinese;
-- 查询总分大于200分 并且 数学成绩小于语文成绩,的姓赵的学生.
-- 赵% 表示 名字以韩开头的就可以
SELECT * FROM student
	WHERE (english + chinese + math) > 200 AND math < chinese AND `name` LIKE '赵%';

-- 查询英语分数在 80－90之间的同学。
SELECT * FROM student
	WHERE english BETWEEN 80 AND 90;
SELECT * FROM student
	WHERE english >= 80 AND english =< 90;
	; -- between .. and .. 是 闭区间
-- 查询数学分数为89,90,91的同学。
SELECT * FROM student
	WHERE math IN(89,90,91);
-- 查询所有姓李的学生成绩。
SELECT * FROM student
	WHERE `name` LIKE '韩%';

-- 查询数学分>80，语文分>80的同学
SELECT * FROM student
	WHERE math > 80 AND chinese > 80;
```



select 使用order by 子句排序查询结果

```mysql
select column1,column2,column3...
	from table
	order by column asc|desc,...
	
-- 1.order by 指定排序的列，排序的列既可以是表中的列名，也可以是select语句后指定的列名。
-- 2.Asc 升序[默认]、Desc 降序
-- 3.ORDER BY 子句应位于select语句的结尾

-- 演示order by使用
-- 对数学成绩排序后输出【升序】。
SELECT * FROM student
	ORDER BY math;
-- 对总分按从高到低的顺序输出 [降序] -- 使用别名排序
SELECT `name`,(english + chinese + math) AS total_score FROM student
	ORDER BY total_score DESC;

-- 对姓韩的学生成绩[总分]排序输出(升序) where + order by

SELECT `name`, (english + chinese + math) AS total_score FROM student
	WHERE `name` LIKE '韩%'
	ORDER BY total_score;
```



合计/统计函数 count

count 返回行的总数

```mysql
select count(*)|count(列名) from table_name
	[where where_definition]
	
-- 统计一个班级共有多少学生？
SELECT COUNT(*) FROM student;
-- 统计数学成绩大于90的学生有多少个？
SELECT COUNT(*) FROM student
	WHERE math > 90;
-- 统计总分大于250的人数有多少？
SELECT COUNT(*) FROM student
	WHERE (math + english + chinese) > 250;
-- count(*) 和 count(列) 的区别 
-- 解释 :count(*) 返回满足条件的记录的行数
-- count(列): 统计满足条件的某列有多少个，但是会排除 为null的情况
CREATE TABLE t15 (
	`name` VARCHAR(20));
INSERT INTO t15 VALUES('tom');
INSERT INTO t15 VALUES('jack');
INSERT INTO t15 VALUES('mary');
INSERT INTO t15 VALUES(NULL);
SELECT * FROM t15;

SELECT COUNT(*) FROM t15;
SELECT COUNT(`name`) FROM t15;
```



合计函数 -sum

sum函数返回满足where条件的行的和 - 一般使用在数值列

```mysql
select sum(列名){,sum(列名)...} from table_name
	[where where_definition]
	
-- sum仅对数值起作用，没有意义。
-- 对多列求和，“，” 号不能少。

-- 演示sum函数的使用
-- 统计一个班级数学总成绩？
SELECT SUM(math) FROM student;
-- 统计一个班级语文、英语、数学各科的总成绩
SELECT SUM(math),SUM(english),SUM(chinese) FROM student;
-- 统计一个班级语文、英语、数学的成绩总和
SELECT SUM(math + english + chinese) FROM student;
-- 统计一个班级语文成绩平均分
SELECT SUM(chinese)/COUNT(*) FROM student;

```



合计函数 -avg

AVG函数返回满足where条件的一列的平均值

```mysql
select avg(列名){,avg(列名)...} from table_name
	[where where_definition]
	
-- 求一个班级数学平均分？
SELECT AVG(math) FROM student;
-- 求一个班级总分平均分
SELECT AVG(math + english + chinese) FROM student;	

```



合计函数 - Max/min

Max/min函数返回满足where条件的一列的最大/最小值

```mysql
select max(列名) from table_name
	[where where_definition]
	
-- 求班级最高分和最低分（数值范围在统计中特别有用）
SELECT MAX(math + english + chinese),MIN(math + english + chinese) FROM student;
SELECT * FROM student;
-- 求出班级数学最高分和最低分
SELECT MAX(math),MIN(math) FROM student;
```



使用group by子句对列进行分组[先创建测试表]

```mysql
select column1,column2,column3,... from table
	group by column
-- group by用于对查询的结果分组统计

-- ?如何显示每个部门的平均工资和最高工资
-- 老韩分析: avg(sal) max(sal)
-- 按照部分来分组查询
SELECT AVG(sal),MAX(sal),deptno FROM emp GROUP BY deptno; 
-- 使用数学方法，对小数点进行处理
SELECT FORMAT(AVG(sal),2),MAX(sal),deptno FROM emp GROUP BY deptno; 
```

使用having 子句对分组后的结果进行过滤

```mysql
select column1,column2,column3,..
	from table
	group by column1 having...
-- having子句用于限制分组显示结果

-- ?显示每个部门的每种岗位的平均工资和最低工资
-- 老师分析 1. 显示每个部门的平均工资和最低工资
--          2. 显示每个部门的每种岗位的平均工资和最低工资
SELECT AVG(sal),MAX(sal),deptno,job FROM emp GROUP BY deptno,job;


-- ?显示平均工资低于2000的部门号和它的平均工资 // 别名

-- 老师分析 [写sql语句的思路是化繁为简,各个击破]
-- 1. 显示各个部门的平均工资和部门号
-- 2. 在1的结果基础上，进行过滤，保留 AVG(sal) < 2000
-- 3. 使用别名进行过滤 
SELECT AVG(sal),deptno FROM emp GROUP BY deptno HAVING AVG(sal) < 2000;
SELECT AVG(sal), deptno FROM emp 
	GROUP BY deptno HAVING AVG(sal) < 2000;

-- 使用别名		
	
SELECT AVG(sal) AS avg_sal, deptno FROM emp 
	GROUP BY deptno HAVING avg_sal < 2000;	
```



字符串相关函数的使用

```mysql
-- charset(str) 返回字串字符集
select charset(ename) from emp;

-- concat(string2 [,...]) 连接字串,将多个列拼接成一列
select concat(ename, ' job is', job) from emp;

-- instr(string,substring)  返回substring在string中出现的位置，没有返回0
-- dual 亚元表，系统表 可以作为测试表使用
select instr('hanshunping', 'ping') from dual;

-- UCASE(string2) 转换成大写
select UCASE(ename) from emp;
-- LCASE(string2) 转换成小写
select lcase(ename) from emp;

-- left(string2,length) 从string2中的左边起取length个字符
select left(ename,2) from emp;

-- length(string) string长度[按照字节]
select length(ename) from emp;

-- replace(str,search str, replace_str)
-- 在str中用replace_str替换search_str
-- 如果是manager 就替换成 经理
select ename, replace(job, 'MANAGER','经理') from emp;

-- strcmp(string1,string2) 逐字符比较两字串大小
select strcmp('hsp','jsp') from dual;

-- substring(str,position,[,length ])
-- 从str的position开始[从1开始计算]，去length个字符
-- 从ename 列的第一个位置开始取出2个字符
select substring(ename,1,2) from emp;

-- ltrim(string2) rtrim(string2) trim(string)
-- 去除前端空格或后端空格
select ltrim(' 韩顺平教育') from dual;
select rtrim('韩顺平教育 ') from dual;
select trim(' 韩顺平教育 ') from dual;


```

数学相关函数

```mysql
ABS(num)    -- 绝对值
select ABS(10) from dual;
BIN(decimal_number)  -- 十进制转二进制
select BIN(10) from dual;
CEILING(number2)  -- 向上取整，得到比num2 大的最小整数
select ceiling(-1.2) from dual;
conv(number2,from_base,to_base)  -- 进制转换
-- 下面的含义8 是十进制的8， 转成2进制输出
select conv(8,10,2) from dual;
-- 下面的含义8 是16进制的8， 转成2进制输出
select conv(8,16,10) from dual;
floor(number2)  -- 向下取整，得到比 num2 小的最大整数
selece floor(1.1) from dual; 
format(number,decimal_places) 保留小数位数(四舍五入)
select format(78.123458) from dual;
hex(decimal_number)  -- 转十六进制
least(number,number [,...])  -- 求最小值
select least(0,1,2) from dual;
mod (numerator,denominator)  -- 求余
select mod(10 ,3) form dual;
rand([seed]) rand([seed]) -- 返回随机数 其范围为 0<= v <= 1.0
-- 如果不给seed种子，随机数不固定
select rand() from dual;
-- 如果给seed种子，随机数固定
select rand(3) from dual;

```

时间日期相关函数

```mysql
-- 日期时间相关函数

-- CURRENT_DATE (  )	当前日期
SELECT CURRENT_DATE() FROM DUAL;
-- CURRENT_TIME (  )	当前时间
SELECT CURRENT_TIME()  FROM DUAL;
-- CURRENT_TIMESTAMP (  ) 当前时间戳
SELECT CURRENT_TIMESTAMP()  FROM DUAL;

-- 创建测试表 信息表
CREATE TABLE mes(
	id INT , 
	content VARCHAR(30), 
	send_time DATETIME);
	
	
-- 添加一条记录
INSERT INTO mes 
	VALUES(1, '北京新闻', CURRENT_TIMESTAMP()); 
INSERT INTO mes VALUES(2, '上海新闻', NOW());
INSERT INTO mes VALUES(3, '广州新闻', NOW());

SELECT * FROM mes;
SELECT NOW() FROM DUAL;

-- 上应用实例
-- 显示所有新闻信息，发布日期只显示 日期，不用显示时间.
SELECT id, content, DATE(send_time) 
	FROM mes;
-- 请查询在10分钟内发布的新闻, 思路一定要梳理一下.
SELECT * 
	FROM mes
	WHERE DATE_ADD(send_time, INTERVAL 10 MINUTE) >= NOW()

SELECT * 
	FROM mes
	WHERE send_time >= DATE_SUB(NOW(), INTERVAL 10 MINUTE) 

-- 请在mysql 的sql语句中求出 2011-11-11 和 1990-1-1 相差多少天
SELECT DATEDIFF('2011-11-11', '1990-01-01') FROM DUAL;
-- 请用mysql 的sql语句求出你活了多少天? [练习] 1986-11-11 出生
SELECT DATEDIFF(NOW(), '1986-11-11') FROM DUAL;
-- 如果你能活80岁，求出你还能活多少天.[练习] 1986-11-11 出生
-- 先求出活80岁 时, 是什么日期 X
-- 然后在使用 datediff(x, now()); 1986-11-11->datetime
-- INTERVAL 80 YEAR ： YEAR 可以是 年月日，时分秒
-- '1986-11-11' 可以date,datetime timestamp 
SELECT DATEDIFF(DATE_ADD('1986-11-11', INTERVAL 80 YEAR), NOW()) 
	FROM DUAL;
	
SELECT TIMEDIFF('10:11:11', '06:10:10') FROM DUAL;

-- YEAR|Month|DAY| DATE (datetime )
SELECT YEAR(NOW()) FROM DUAL;
SELECT MONTH(NOW()) FROM DUAL;
SELECT DAY(NOW()) FROM DUAL;
SELECT MONTH('2013-11-10') FROM DUAL;
-- unix_timestamp() : 返回的是1970-1-1 到现在的秒数
SELECT UNIX_TIMESTAMP() FROM DUAL;
-- FROM_UNIXTIME() : 可以把一个unix_timestamp 秒数[时间戳]，转成指定格式的日期
-- %Y-%m-%d 格式是规定好的，表示年月日
-- 意义：在开发中，可以存放一个整数，然后表示时间，通过FROM_UNIXTIME转换
--   
SELECT FROM_UNIXTIME(1618483484, '%Y-%m-%d') FROM DUAL;
SELECT FROM_UNIXTIME(1618483100, '%Y-%m-%d %H:%i:%s') FROM DUAL;

SELECT * FROM mysql.user \G 

```

加密函数和系统函数

```mysql
-- USER()  查询用户
-- 可以查看登陆到mysql的有哪些用户，以及登陆的ip
select user() from dual;  -- 用户@IP地址

-- database()  查询当前使用数据库名称
select database() from dual;

-- MD5(str)  为字符串算出一个 MD5 32的字符串，常用(用户密码) 加密
select md5('hsp') from dual;

--  演示用户表，存放密码时，是md5
create table hsp_user(
		id int,
    	`name` varchar(32) not null default '',
    	pwd char(32) not null default ``);
insert into hsp_user values(100,'韩顺平',md5('hsp'));
SELECT * FROM hsp_user
	WHERE `name` = '韩顺平' AND pwd = MD5('hsp');
	
-- password(str)  -- 加密函数
select password('hsp') from dual;

-- select * from mysql.user \G 从原文密码str 计算并返回密码字符串
-- 通常用于对mysql数据库的用户密码加密
-- mysql.user 表示 数据库.表
select * from mysql.user;

```



流程控制函数

```mysql
# 演示流程控制语句
# IF(expr1,expr2,expr3)	如果expr1为True ,则返回 expr2 否则返回 expr3
SELECT IF(TRUE, '北京', '上海') FROM DUAL;
# IFNULL(expr1,expr2)	如果expr1不为空NULL,则返回expr1,否则返回expr2
SELECT IFNULL( NULL, '韩顺平教育') FROM DUAL;
# SELECT CASE WHEN expr1 THEN expr2 WHEN expr3 THEN expr4 ELSE expr5 END; [类似多重分支.]
# 如果expr1 为TRUE,则返回expr2,如果expr2 为t, 返回 expr4, 否则返回 expr5

SELECT CASE 
	WHEN TRUE THEN 'jack'  -- jack
	WHEN FALSE THEN 'tom' 
	ELSE 'mary' END

-- 1. 查询emp 表, 如果 comm 是null , 则显示0.0
--    老师说明，判断是否为null 要使用 is null, 判断不为空 使用 is not
SELECT ename, IF(comm IS NULL , 0.0, comm)
	FROM emp;
SELECT ename, IFNULL(comm, 0.0)
	FROM emp;
-- 2. 如果emp 表的 job 是 CLERK 则显示 职员， 如果是 MANAGER 则显示经理
--     如果是 SALESMAN 则显示 销售人员，其它正常显示

SELECT ename, (SELECT CASE 
		WHEN job = 'CLERK' THEN '职员' 
		WHEN job = 'MANAGER' THEN '经理'
		WHEN job = 'SALESMAN' THEN '销售人员' 
		ELSE job END) AS 'job',job
	FROM emp; 

SELECT * FROM emp;
SELECT * FROM dept;
SELECT * FROM salgrade;
```



mysql表查询-加强

```mysql
-- 查询加强
-- ■ 使用where子句
-- 	?如何查找1992.1.1后入职的员工
-- 老师说明： 在mysql中,日期类型可以直接比较, 需要注意格式
SELECT * FROM emp
	WHERE hiredate > '1992-01-01'
-- ■ 如何使用like操作符(模糊)
-- 	%: 表示0到多个任意字符 _: 表示单个任意字符
-- 	?如何显示首字符为S的员工姓名和工资
SELECT ename, sal FROM emp
	WHERE ename LIKE 'S%'
-- 	?如何显示第三个字符为大写O的所有员工的姓名和工资
SELECT ename, sal FROM emp
	WHERE ename LIKE '__O%'

-- ■ 如何显示没有上级的雇员的情况
SELECT * FROM emp
	WHERE mgr IS NULL;
-- ■ 查询表结构 
DESC emp 

-- 使用order by子句
--   ?如何按照工资的从低到高的顺序[升序]，显示雇员的信息
SELECT * FROM emp
	ORDER BY sal 
--   ?按照部门号升序而雇员的工资降序排列 , 显示雇员信息

SELECT * FROM emp
	ORDER BY deptno ASC , sal DESC;


```

mysql表查询-加强

分页查询

```mysql
-- 分页查询
-- 按雇员的id号升序取出， 每页显示3条记录，请分别显示 第1页，第2页，第3页

-- 第1页
SELECT * FROM emp 
	ORDER BY empno 
	LIMIT 0, 3;
-- 第2页
SELECT * FROM emp 
	ORDER BY empno 
	LIMIT 3, 3;
-- 第3页
SELECT * FROM emp 
	ORDER BY empno 
	LIMIT 6, 3;
-- 推导一个公式 
SELECT * FROM emp
	ORDER BY empno 
	LIMIT #每页显示记录数 * (第几页-1) , 每页显示记录数
	
	
-- 测试
SELECT job, COUNT(*) FROM emp GROUP BY  job;
-- 显示雇员总数，以及获得补助的雇员数
SELECT COUNT(*) FROM emp  WHERE mgr IS NOT NULL;
SELECT MAX(sal) - MIN(sal) FROM emp;
	
	
	
```

mysql表查询-加强

group by 的使用

```mysql
-- 增强group by 的使用

-- (1) 显示每种岗位的雇员总数、平均工资。
SELECT COUNT(*), AVG(sal), job 
	FROM emp 
	GROUP BY job; 
-- (2) 显示雇员总数，以及获得补助的雇员数。
--  思路: 获得补助的雇员数 就是 comm 列为非null, 就是count(列)，如果该列的值为null, 是
--  不会统计 , SQL 非常灵活，需要我们动脑筋.
SELECT COUNT(*), COUNT(comm)
	FROM emp 

--  老师的扩展要求：统计没有获得补助的雇员数
SELECT COUNT(*), COUNT(IF(comm IS NULL, 1, NULL))
	FROM emp 

SELECT COUNT(*), COUNT(*) - COUNT(comm)
	FROM emp 

-- (3) 显示管理者的总人数。小技巧:尝试写->修改->尝试[正确的]
SELECT COUNT(DISTINCT mgr) 
	FROM emp; 

-- (4) 显示雇员工资的最大差额。
-- 思路： max(sal) - min(sal)
SELECT MAX(sal) - MIN(sal) 
	FROM emp;

SELECT * FROM e
mp;
SELECT * FROM dept;



-- 应用案例：请统计各个部门group by 的平均工资 avg，
-- 并且是大于1000的 having，并且按照平均工资从高到低排序， order by
-- 取出前两行记录 limit 0, 2

SELECT deptno, AVG(sal) AS avg_sal
	FROM emp
	GROUP BY deptno
	HAVING  avg_sal > 1000
	ORDER BY avg_sal DESC
	LIMIT 0,2 
```

### 数据分组的总结

```mysql
-- 如果select 语句同事包含有group by,having,limit,order by 那么他们的顺序是group by,having,order by,limit
-- 用format()函数的要注意，它的返回类型是string,不可以直接跟数字进行比较
```

### mysql 多表查询

![image-20221130204632007](https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221130204632007.png)

 

![image-20221130205527646](https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221130205527646.png)



```mysql
-- 多表查询
-- ?显示雇员名,雇员工资及所在部门的名字 【笛卡尔集】
/*
	老韩分析
	1. 雇员名,雇员工资 来自 emp表
	2. 部门的名字 来自 dept表
	3. 需求对 emp 和 dept查询  ename,sal,dname,deptno
	4. 当我们需要指定显示某个表的列是，需要 表.列表
*/
SELECT ename,sal,dname,emp.deptno
	FROM emp, dept 
	WHERE emp.deptno = dept.deptno
	
SELECT * FROM emp;
SELECT * FROM dept;
SELECT * FROM salgrade;
-- 老韩小技巧：多表查询的条件不能少于 表的个数-1, 否则会出现笛卡尔集
-- ?如何显示部门号为10的部门名、员工名和工资 
SELECT ename,sal,dname,emp.deptno
	FROM emp, dept 
	WHERE emp.deptno = dept.deptno AND emp.deptno = 10

-- ?显示各个员工的姓名，工资，及其工资的级别

-- 思路 姓名，工资 来自 emp 13
--      工资级别 salgrade 5
-- 写sql , 先写一个简单，然后加入过滤条件...
SELECT ename, sal, grade 
	FROM emp , salgrade
	WHERE sal BETWEEN losal AND hisal; 

```

### 多表查询 自连接

自连接是指在同一张表的连接查询[将同一张表看做两张表]

```mysql
-- 多表查询的 自连接

-- 思考题: 显示公司员工名字和他的上级的名字

-- 老韩分析： 员工名字 在emp, 上级的名字的名字 emp
-- 员工和上级是通过 emp表的 mgr 列关联
-- 这里老师小结：
-- 自连接的特点 1. 把同一张表当做两张表使用
--               2. 需要给表取别名 表名  表别名 
--		 3. 列名不明确，可以指定列的别名 列名 as 列的别名		
SELECT worker.ename AS '职员名' ,  boss.ename AS '上级名'
	FROM emp worker, emp boss
	WHERE worker.mgr = boss.empno;
SELECT * FROM emp;
```

### mysql表子查询

**什么是子查询**

**子查询是指嵌入在其它sql语句中的select语句，也叫嵌套查询**

1. **单行子查询**

​		**单行子查询是指只返回一行数据的子查询语句**

```mysql
-- 子查询的演示
-- 请思考：如何显示与SMITH同一部门的所有员工?
/*
	1. 先查询到 SMITH的部门号得到
	2. 把上面的select 语句当做一个子查询来使用
*/
SELECT deptno 
	FROM emp 
	WHERE ename = 'SMITH'

-- 下面的答案.	
SELECT * 
	FROM emp
	WHERE deptno = (
		SELECT deptno 
		FROM emp 
		WHERE ename = 'SMITH'
```



2. **多行子查询**

​		**多行子查询指返回多行数据的子查询，使用关键字 in**

```mysql
-- 课堂练习:如何查询和部门10的工作相同的雇员的
-- 名字、岗位、工资、部门号, 但是不含10号部门自己的雇员.

/*
	1. 查询到10号部门有哪些工作
	2. 把上面查询的结果当做子查询使用
*/
SELECT DISTINCT job 
	FROM emp 
	WHERE deptno = 10;
	
--  下面语句完整

SELECT ename, job, sal, deptno
	FROM emp
	WHERE job IN (
		SELECT DISTINCT job 
		FROM emp 
		WHERE deptno = 10
	) AND deptno != 10 
	
```



### mysql表子查询

子查询当做临时表使用

```mysql
-- 查询ecshop中各个类别中，价格最高的商品

-- 查询 商品表
-- 先得到 各个类别中，价格最高的商品 max + group by cat_id, 当做临时表
-- 把子查询当做一张临时表可以解决很多很多复杂的查询

SELECT cat_id , MAX(shop_price) 
	FROM ecs_goods
	GROUP BY cat_id
	
	
-- 这个最后答案	
SELECT goods_id, ecs_goods.cat_id, goods_name, shop_price 
	FROM (
		SELECT cat_id , MAX(shop_price) AS max_price
		FROM ecs_goods
		GROUP BY cat_id
	) temp , ecs_goods
	WHERE  temp.cat_id = ecs_goods.cat_id 
	AND temp.max_price = ecs_goods.shop_price 
```



### myslq表子查询

#### 在多行子查询中使用all和any操作符

```mysql
-- all 和 any的使用

-- 请思考:显示工资比部门30的所有员工的工资高的员工的姓名、工资和部门号

SELECT ename, sal, deptno
	FROM emp
	WHERE sal > ALL(
		SELECT sal 
			FROM emp
			WHERE deptno = 30
		) 
-- 可以这样写
SELECT ename, sal, deptno
	FROM emp
	WHERE sal > (
		SELECT MAX(sal) 
			FROM emp
			WHERE deptno = 30
		) 

-- 请思考:如何显示工资比部门30的其中一个员工的工资高的员工的姓名、工资和部门号

SELECT ename, sal, deptno
	FROM emp
	WHERE sal > ANY(
		SELECT sal 
			FROM emp
			WHERE deptno = 30
		)

 SELECT ename, sal, deptno
	FROM emp
	WHERE sal > (
		SELECT MIN(sal) 
			FROM emp
			WHERE deptno = 30
		)

```



### mysql表子查询 多列子查询

```mysql
-- 多列子查询

-- 请思考如何查询与allen的部门和岗位完全相同的所有雇员(并且不含allen本人)
-- (字段1， 字段2 ...) = (select 字段 1，字段2 from 。。。。)

-- 分析: 1. 得到smith的部门和岗位

SELECT deptno , job
	FROM emp 
	WHERE ename = 'ALLEN'
	
-- 分析: 2  把上面的查询当做子查询来使用，并且使用多列子查询的语法进行匹配
SELECT * 
	FROM emp
	WHERE (deptno , job) = (
		SELECT deptno , job
		FROM emp 
		WHERE ename = 'ALLEN'
	) AND ename != 'ALLEN'



-- 请查询 和宋江数学，英语，语文   
-- 成绩 完全相同的学生
SELECT * 
	FROM student
	WHERE (math, english, chinese) = (
		SELECT math, english, chinese
		FROM student
		WHERE `name` = '宋江'
	)

SELECT * FROM student;


--
	

```



### mysql表子查询 在from子句中使用子查询

```mysql
-- 子查询练习

-- 请思考：查找每个部门工资高于本部门平均工资的人的资料
-- 这里要用到数据查询的小技巧，把一个子查询当作一个临时表使用

-- 1. 先得到每个部门的 部门号和 对应的平均工资

SELECT deptno, AVG(sal) AS avg_sal
	FROM emp GROUP BY deptno
	
-- 2. 把上面的结果当做子查询, 和 emp 进行多表查询
--    
SELECT ename, sal, temp.avg_sal, emp.deptno
	FROM emp, (
		SELECT deptno, AVG(sal) AS avg_sal
		FROM emp 
		GROUP BY deptno
	) temp 
	WHERE emp.deptno = temp.deptno AND emp.sal > temp.avg_sal
	
-- 查找每个部门工资最高的人的详细资料

SELECT ename, sal, temp.max_sal, emp.deptno
	FROM emp, (
		SELECT deptno, MAX(sal) AS max_sal
		FROM emp 
		GROUP BY deptno
	) temp 
	WHERE emp.deptno = temp.deptno AND emp.sal = temp.max_sal
	

-- 查询每个部门的信息(包括：部门名,编号,地址)和人员数量,我们一起完成。

-- 1. 部门名,编号,地址 来自 dept表
-- 2. 各个部门的人员数量 -》 构建一个临时表

SELECT COUNT(*), deptno 
	FROM emp
	GROUP BY deptno;
	

SELECT dname, dept.deptno, loc , tmp.per_num AS '人数'
	FROM dept, (
		SELECT COUNT(*) AS per_num, deptno 
		FROM emp
		GROUP BY deptno
	) tmp 
	WHERE tmp.deptno = dept.deptno

-- 还有一种写法 表.* 表示将该表所有列都显示出来, 可以简化sql语句
-- 在多表查询中，当多个表的列不重复时，才可以直接写列名

SELECT tmp.* , dname, loc
	FROM dept, (
		SELECT COUNT(*) AS per_num, deptno 
		FROM emp
		GROUP BY deptno
	) tmp 
	WHERE tmp.deptno = dept.deptno
	

```

### 表赋值 自我复制数据

```mysql
-- 表的复制
-- 为了对某个sql语句进行效率测试，我们需要海量数据时，可以使用此法为表创建海量数据

CREATE TABLE my_tab01 
	( id INT,
	  `name` VARCHAR(32),
	  sal DOUBLE,
	  job VARCHAR(32),
	  deptno INT);
DESC my_tab01
SELECT * FROM my_tab01;

-- 演示如何自我复制
-- 1. 先把emp 表的记录复制到 my_tab01
INSERT INTO my_tab01 
	(id, `name`, sal, job,deptno)
	SELECT empno, ename, sal, job, deptno FROM emp;
-- 2. 自我复制
INSERT INTO my_tab01
	SELECT * FROM my_tab01;
SELECT COUNT(*) FROM my_tab01;

-- 如何删除掉一张表重复记录
-- 1. 先创建一张表 my_tab02, 
-- 2. 让 my_tab02 有重复的记录

CREATE TABLE my_tab02 LIKE emp; -- 这个语句 把emp表的结构(列)，复制到my_tab02

DESC my_tab02;

INSERT INTO my_tab02
	SELECT * FROM emp;
SELECT * FROM my_tab02;
-- 3. 考虑去重 my_tab02的记录
/*
	思路 
	(1) 先创建一张临时表 my_tmp , 该表的结构和 my_tab02一样
	(2) 把my_tmp 的记录 通过 distinct 关键字 处理后 把记录复制到 my_tmp
	(3) 清除掉 my_tab02 记录
	(4) 把 my_tmp 表的记录复制到 my_tab02
	(5) drop 掉 临时表my_tmp
*/
-- (1) 先创建一张临时表 my_tmp , 该表的结构和 my_tab02一样

CREATE TABLE my_tmp LIKE my_tab02
-- (2) 把my_tmp 的记录 通过 distinct 关键字 处理后 把记录复制到 my_tmp
INSERT INTO my_tmp 
	SELECT DISTINCT * FROM my_tab02;

-- (3) 清除掉 my_tab02 记录
DELETE FROM my_tab02;
-- (4) 把 my_tmp 表的记录复制到 my_tab02
INSERT INTO my_tab02
	SELECT * FROM my_tmp;
-- (5) drop 掉 临时表my_tmp
DROP TABLE my_tmp;



SELECT * FROM my_tab02;


	
	
```

### 合并查询

```mysql
-- 合并查询

SELECT ename,sal,job FROM emp WHERE sal>2500 -- 5

SELECT ename,sal,job FROM emp WHERE job='MANAGER' -- 3

-- union all 就是将两个查询结果合并，不会去重
SELECT ename,sal,job FROM emp WHERE sal>2500 -- 5
UNION ALL
SELECT ename,sal,job FROM emp WHERE job='MANAGER' -- 3

-- union  就是将两个查询结果合并，会去重
SELECT ename,sal,job FROM emp WHERE sal>2500 -- 5
UNION 
SELECT ename,sal,job FROM emp WHERE job='MANAGER' -- 3
```

### mysql表外连接

1. 左外连接(如果左侧的表完全显示我们就说是左外连接)

2. 右外连接(如果右侧的表完全显示我们就说是右外连接)

3. 使用左外连接

   ```mysql
   select...
   	from 表1 left join 表2  -- [表1：就是左表 表2: 就是右表]
   	on 条件
   ```

4. 使用右外连接

   ```mysql
   select...
   	from 表1 right join 表2  -- [表1：就是左表 表2: 就是右表]
   	on 条件
   ```

5. 老师小结：在实际的开发中，我们绝大多数情况下使用的是 前面学过的连接



### mysql约束，主键(primary key)  --细节说明

```mysql
-- 主键使用

-- id	name 	email
CREATE TABLE t17
	(id INT PRIMARY KEY, -- 表示id列是主键 
	`name` VARCHAR(32),
	email VARCHAR(32));
	
-- 主键列的值是不可以重复
INSERT INTO t17
	VALUES(1, 'jack', 'jack@sohu.com');
INSERT INTO t17
	VALUES(2, 'tom', 'tom@sohu.com');

INSERT INTO t17
	VALUES(1, 'hsp', 'hsp@sohu.com');
	
SELECT * FROM t17;

-- 主键使用的细节讨论
-- primary key不能重复而且不能为 null。
INSERT INTO t17
	VALUES(NULL, 'hsp', 'hsp@sohu.com');
-- 一张表最多只能有一个主键, 但可以是复合主键(比如 id+name)
CREATE TABLE t18
	(id INT PRIMARY KEY, -- 表示id列是主键 
	`name` VARCHAR(32), PRIMARY KEY -- 错误的
	email VARCHAR(32));
-- 演示复合主键 (id 和 name 做成复合主键)
CREATE TABLE t18
	(id INT , 
	`name` VARCHAR(32), 
	email VARCHAR(32),
	PRIMARY KEY (id, `name`) -- 这里就是复合主键
	);

INSERT INTO t18
	VALUES(1, 'tom', 'tom@sohu.com');
INSERT INTO t18
	VALUES(1, 'jack', 'jack@sohu.com');
INSERT INTO t18
	VALUES(1, 'tom', 'xx@sohu.com'); -- 这里就违反了复合主键
SELECT * FROM t18;

-- 主键的指定方式 有两种 
-- 1. 直接在字段名后指定：字段名  primakry key
-- 2. 在表定义最后写 primary key(列名); 
CREATE TABLE t19
	(id INT , 
	`name` VARCHAR(32) PRIMARY KEY, 
	email VARCHAR(32)
	);

CREATE TABLE t20
	(id INT , 
	`name` VARCHAR(32) , 
	email VARCHAR(32),
	PRIMARY KEY(`name`) -- 在表定义最后写 primary key(列名)
	);
 
-- 使用desc 表名，可以看到primary key的情况

DESC t20 -- 查看 t20表的结果，显示约束的情况
DESC t18

-- 老师提醒: 在实际开发中，每个表往往都会设计一个主键

```

### not null(费控)

### unique(唯一的) 

当定义了唯一约束后，该列值是不能重复的

```mysql
-- unique的使用

CREATE TABLE t21
	(id INT UNIQUE ,  -- 表示 id 列是不可以重复的.
	`name` VARCHAR(32) , 
	email VARCHAR(32)
	);
	
INSERT INTO t21
	VALUES(1, 'jack', 'jack@sohu.com');

INSERT INTO t21
	VALUES(1, 'tom', 'tom@sohu.com');
	
-- unqiue使用细节
-- 1. 如果没有指定 not null , 则 unique 字段可以有多个null
-- 如果一个列(字段)， 是 unique not null 使用效果类似 primary key
INSERT INTO t21
	VALUES(NULL, 'tom', 'tom@sohu.com');
SELECT * FROM t21;
-- 2. 一张表可以有多个unique字段

CREATE TABLE t22
	(id INT UNIQUE ,  -- 表示 id 列是不可以重复的.
	`name` VARCHAR(32) UNIQUE , -- 表示name不可以重复 
	email VARCHAR(32)
	);
DESC t22


```

### mysql约束  foreign key(外键)

<img src="https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221210160105746.png" alt="image-20221210160105746" style="zoom:50%;" />

<img src="https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221210160110512.png" alt="image-20221210160110512" style="zoom:50%;" />

```mysql
-- 外键演示

-- 创建 主表 my_class
CREATE TABLE my_class (
	id INT PRIMARY KEY , -- 班级编号
	`name` VARCHAR(32) NOT NULL DEFAULT '');

-- 创建 从表 my_stu
CREATE TABLE my_stu (
	id INT PRIMARY KEY , -- 学生编号
	`name` VARCHAR(32) NOT NULL DEFAULT '',
	class_id INT , -- 学生所在班级的编号
	-- 下面指定外键关系
	FOREIGN KEY (class_id) REFERENCES my_class(id))
-- 测试数据
INSERT INTO my_class 
	VALUES(100, 'java'), (200, 'web');
INSERT INTO my_class 
	VALUES(300, 'php');
	
SELECT * FROM my_class;
INSERT INTO my_stu 
	VALUES(1, 'tom', 100);
INSERT INTO my_stu 
	VALUES(2, 'jack', 200);
INSERT INTO my_stu 
	VALUES(3, 'hsp', 300);
INSERT INTO my_stu 
	VALUES(4, 'mary', 400); -- 这里会失败...因为400班级不存在

INSERT INTO my_stu 
	VALUES(5, 'king', NULL); -- 可以, 外键 没有写 not null
SELECT * FROM my_class;

-- 一旦建立主外键的关系，数据不能随意删除了
DELETE FROM my_class
	WHERE id = 100; 
	
```



### mysql check:用于强制行数据必须满足的条件

<img src="https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221210221800511.png" alt="image-20221210221800511" style="zoom:50%;" />

```mysql
-- 演示check的使用
-- mysql5.7目前还不支持check ,只做语法校验，但不会生效
-- 了解 
-- 学习 oracle, sql server, 这两个数据库是真的生效.

-- 测试
CREATE TABLE t23 (
	id INT PRIMARY KEY,
	`name` VARCHAR(32) ,
	sex VARCHAR(6) CHECK (sex IN('man','woman')),
	sal DOUBLE CHECK ( sal > 1000 AND sal < 2000)
	);
	
-- 添加数据
INSERT INTO t23 
	VALUES(1, 'jack', 'mid', 1);
SELECT * FROM t23;
```



###  使用约束的课堂练习

```mysql
-- 使用约束的课堂练习

CREATE DATABASE shop_db;

-- 现有一个商店的数据库shop_db，记录客户及其购物情况，由下面三个表组成：
-- 商品goods（商品号goods_id，商品名goods_name，单价unitprice，商品类别category，
-- 供应商provider);
-- 客户customer（客户号customer_id,姓名name,住址address,电邮email性别sex,身份证card_Id);
-- 购买purchase（购买订单号order_id，客户号customer_id,商品号goods_id,购买数量nums);
-- 1 建表，在定义中要求声明 [进行合理设计]：
-- (1)每个表的主外键；
-- (2)客户的姓名不能为空值；
-- (3)电邮不能够重复;
-- (4)客户的性别[男|女] check 枚举..
-- (5)单价unitprice 在 1.0 - 9999.99 之间 check

-- 商品goods
CREATE TABLE goods (
	goods_id INT PRIMARY KEY,
	goods_name VARCHAR(64) NOT NULL DEFAULT '',
	unitprice DECIMAL(10,2) NOT NULL DEFAULT 0 
		CHECK (unitprice >= 1.0 AND unitprice <= 9999.99),
	category INT NOT NULL DEFAULT 0,
	provider VARCHAR(64) NOT NULL DEFAULT '');
	
-- 客户customer（客户号customer_id,姓名name,住址address,电邮email性别sex,
-- 身份证card_Id);
CREATE TABLE customer(
	customer_id CHAR(8) PRIMARY KEY, -- 程序员自己决定
	`name` VARCHAR(64) NOT NULL DEFAULT '',
	address VARCHAR(64) NOT NULL DEFAULT '',
	email VARCHAR(64) UNIQUE NOT NULL,
	sex ENUM('男','女') NOT NULL ,  -- 这里老师使用的枚举类型, 是生效
	card_Id CHAR(18)); 
	
-- 购买purchase（购买订单号order_id，客户号customer_id,商品号goods_id,
-- 购买数量nums);
CREATE TABLE purchase(
	order_id INT UNSIGNED PRIMARY KEY,
	customer_id CHAR(8) NOT NULL DEFAULT '', -- 外键约束在后
	goods_id INT NOT NULL DEFAULT 0 , -- 外键约束在后
	nums INT NOT NULL DEFAULT 0,
	FOREIGN KEY (customer_id) REFERENCES customer(customer_id),
	FOREIGN KEY (goods_id) REFERENCES goods(goods_id));
DESC goods;
DESC customer;
DESC purchase;
```



### mysql 自增长

<img src="https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221211233235038.png" alt="image-20221211233235038" style="zoom: 67%;" />

<img src="https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221211233228619.png" alt="image-20221211233228619" style="zoom: 67%;" />

```mysql
-- 自增长（auto-increment）指的是自动为数据库表中的某个字段设置唯一的数字值。例如，您可以将某个字段设置为自增长，并为每个新记录设置该字段的值。这有助于保证数据库表中每个记录都有唯一的编号。

-- 要在 MySQL 数据库表中设置自增长，您可以使用以下 SQL 语句：

CREATE TABLE table_name (
    column_1 INTEGER AUTO_INCREMENT,
    column_2 VARCHAR(255),
    ...
    PRIMARY KEY (column_1)
);

-- 在这个例子中，column_1 字段被设置为自增长，并且被用作数据库表的主键。每次新记录被插入到表中时，column_1 字段的值就会自动增加。

-- 需要注意的是，自增长字段只能为整数类型，例如 INTEGER 或 BIGINT。并且，每个表只能有一个自增长字段。


-- 演示自增长的使用
-- 创建表
CREATE TABLE t24
	(id INT PRIMARY KEY AUTO_INCREMENT,
	 email VARCHAR(32)NOT NULL DEFAULT '',
	 `name` VARCHAR(32)NOT NULL DEFAULT ''); 
DESC t24
-- 测试自增长的使用
INSERT INTO t24
	VALUES(NULL, 'tom@qq.com', 'tom');

INSERT INTO t24
	(email, `name`) VALUES('hsp@sohu.com', 'hsp');

SELECT * FROM t24;

-- 修改默认的自增长开始值
ALTER TABLE t25 AUTO_INCREMENT = 100
CREATE TABLE t25
	(id INT PRIMARY KEY AUTO_INCREMENT,
	 email VARCHAR(32)NOT NULL DEFAULT '',
	 `name` VARCHAR(32)NOT NULL DEFAULT ''); 
INSERT INTO t25
	VALUES(NULL, 'mary@qq.com', 'mary');
INSERT INTO t25
	VALUES(666, 'hsp@qq.com', 'hsp');
SELECT * FROM t25;

CREATE DATABASE tmp;
CREATE TABLE dept( /*部门表*/
deptno MEDIUMINT   UNSIGNED  NOT NULL  DEFAULT 0,
dname VARCHAR(20)  NOT NULL  DEFAULT "",
loc VARCHAR(13) NOT NULL DEFAULT ""
) ;

#创建表EMP雇员
CREATE TABLE emp
(empno  MEDIUMINT UNSIGNED  NOT NULL  DEFAULT 0, /*编号*/
ename VARCHAR(20) NOT NULL DEFAULT "", /*名字*/
job VARCHAR(9) NOT NULL DEFAULT "",/*工作*/
mgr MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,/*上级编号*/
hiredate DATE NOT NULL,/*入职时间*/
sal DECIMAL(7,2)  NOT NULL,/*薪水*/
comm DECIMAL(7,2) NOT NULL,/*红利*/
deptno MEDIUMINT UNSIGNED NOT NULL DEFAULT 0 /*部门编号*/
) ;

#工资级别表
CREATE TABLE salgrade
(
grade MEDIUMINT UNSIGNED NOT NULL DEFAULT 0,
losal DECIMAL(17,2)  NOT NULL,
hisal DECIMAL(17,2)  NOT NULL
);

#测试数据
INSERT INTO salgrade VALUES (1,700,1200);
INSERT INTO salgrade VALUES (2,1201,1400);
INSERT INTO salgrade VALUES (3,1401,2000);
INSERT INTO salgrade VALUES (4,2001,3000);
INSERT INTO salgrade VALUES (5,3001,9999);	

```

### mysql索引

<img src="https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221211233323571.png" alt="image-20221211233323571" style="zoom:67%;" />

```mysql
-- 演示mysql的索引的使用
-- 创建索引
CREATE TABLE t25 (
	id INT ,
	`name` VARCHAR(32));
	
-- 查询表是否有索引
SHOW INDEXES FROM t25;
-- 添加索引
-- 添加唯一索引 
CREATE UNIQUE INDEX id_index ON t25 (id);
-- 添加普通索引方式1
CREATE INDEX id_index ON t25 (id);
-- 如何选择 
-- 1. 如果某列的值，是不会重复的，则优先考虑使用unique索引, 否则使用普通索引
-- 添加普通索引方式2
ALTER TABLE t25 ADD INDEX id_index (id)

-- 添加主键索引
CREATE TABLE t26 (
	id INT ,
	`name` VARCHAR(32));
ALTER TABLE t26 ADD PRIMARY KEY (id)

SHOW INDEX FROM t25

-- 删除索引
DROP INDEX id_index ON t25
-- 删除主键索引
ALTER TABLE t26 DROP PRIMARY KEY


-- 修改索引 ， 先删除，在添加新的索引

-- 查询索引
-- 1. 方式
SHOW INDEX FROM t25
-- 2. 方式
SHOW INDEXES FROM t25
-- 3. 方式
SHOW KEYS FROM t25
-- 4 方式
DESC t25

```

<img src="https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221211235950538.png" alt="image-20221211235950538" style="zoom:67%;" />

<img src="https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221214220406320.png" alt="image-20221214220406320" style="zoom:67%;" />

<img src="https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221212000122590.png" alt="image-20221212000122590" style="zoom:67%;" />

### 主键本来就是索引的一种



### 小结 哪些列上适合使用索引

<img src="https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221216000856018.png" alt="image-20221216000856018" style="zoom:67%;" />



### mysql 事物

<img src="https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221216002012779.png" alt="image-20221216002012779" style="zoom:67%;" />

<img src="https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221217221842329.png" alt="image-20221217221842329" style="zoom:67%;" />



![image-20221217221907068](https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221217221907068.png)

### 回退事务

![image-20221217221937182](https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221217221937182.png)

### 提交事务

![image-20221217221951625](https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221217221951625.png)

### 事务细节讨论

![image-20221217222003950](https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221217222003950.png)



```mysql
-- 事务的一个重要的概念和具体操作
-- 看一个图[看示意图]
-- 演示
-- 1. 创建一张测试表
CREATE TABLE t27
	( id INT,
	  `name` VARCHAR(32));
-- 2. 开始事务
START TRANSACTION 
-- 3. 设置保存点
SAVEPOINT a
-- 执行dml 操作
INSERT INTO t27 VALUES(100, 'tom');
SELECT * FROM t27;

SAVEPOINT b
-- 执行dml操作
INSERT INTO t27 VALUES(200, 'jack');

-- 回退到 b
ROLLBACK TO b
-- 继续回退 a
ROLLBACK TO a
-- 如果这样, 表示直接回退到事务开始的状态.
ROLLBACK 
COMMIT
```

```mysql
-- 讨论 事务细节
-- 1. 如果不开始事务，默认情况下，dml操作是自动提交的，不能回滚
INSERT INTO t27 VALUES(300, 'milan'); -- 自动提交 commit

SELECT * FROM t27

-- 2. 如果开始一个事务，你没有创建保存点. 你可以执行 rollback，
-- 默认就是回退到你事务开始的状态
START TRANSACTION 
INSERT INTO t27 VALUES(400, 'king');
INSERT INTO t27 VALUES(500, 'scott');
ROLLBACK -- 表示直接回退到事务开始的的状态
COMMIT;

-- 3. 你也可以在这个事务中(还没有提交时), 创建多个保存点.比如: savepoint 	aaa;    
-- 执行 dml , savepoint  bbb

-- 4. 你可以在事务没有提交前，选择回退到哪个保存点
-- 5. InnoDB 存储引擎支持事务 , MyISAM 不支持
-- 6. 开始一个事务 start  transaction,    set autocommit=off;

```



### 事物隔离级别

![image-20221217223410785](https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221217223410785.png)



![image-20221217223421560](https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221217223421560.png)



### 演示mysql的事务隔离级别

```mysql
-- 演示mysql的事务隔离级别

-- 1. 开了两个mysql的控制台
-- 2. 查看当前mysql的隔离级别
SELECT @@tx_isolation;

-- mysql> SELECT @@tx_isolation;
-- +-----------------+
-- | @@tx_isolation  |
-- +-----------------+
-- | REPEATABLE-READ |
-- +-----------------+

-- 3.把其中一个控制台的隔离级别设置 Read uncommitted

SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED

-- 4. 创建表
CREATE TABLE `account`(
	id INT,
	`name` VARCHAR(32),
	money INT);
	

-- 查看当前会话隔离级别 
SELECT @@tx_isolation
-- 查看系统当前隔离级别
SELECT @@global.tx_isolation
-- 设置当前会话隔离级别
SET SESSION TRANSACTION ISOLATION LEVEL READ UNCOMMITTED
-- 设置系统当前隔离级别
SET GLOBAL TRANSACTION ISOLATION LEVEL [你设置的级别]
```







MySQL 事务是 MySQL 数据库管理系统中的一个重要特性。事务是一组一起执行的 SQL 语句，这些语句要么全部执行，要么全部不执行。在一个事务中，如果发生了错误或者程序崩溃，那么所有已经执行的语句都会被回滚到事务开始之前的状态。这样可以保证数据的完整性和一致性。

在 MySQL 中，使用 `START TRANSACTION` 和 `COMMIT` 命令来开始和结束一个事务。如果要回滚事务，可以使用 `ROLLBACK` 命令。例如：

```mysql
START TRANSACTION;

UPDATE accounts SET balance = balance - 100 WHERE name = 'Alice';
UPDATE accounts SET balance = balance + 100 WHERE name = 'Bob';

COMMIT;
```

如果发生错误或程序崩溃，上面的事务会被回滚到开始之前的状态，这样 Alice 和 Bob 的账户余额不会改变。

在 MySQL 中，还可以使用 `SAVEPOINT` 命令来创建一个保存点，并使用 `ROLLBACK TO SAVEPOINT` 命令来回滚到保存点。这样可以在一个事务中执行多个操作，如果某个操作出错，可以选择回滚到保存点，而不是回滚整个事务。例如：

```mysql
START TRANSACTION;

UPDATE accounts SET balance = balance - 100 WHERE name = 'Alice';

SAVEPOINT sp1;

UPDATE accounts SET balance = balance + 100 WHERE name = 'Bob';

ROLLBACK TO SAVEPOINT sp1;

COMMIT;
```



对于上面的例子，如果在执行 `UPDATE accounts SET balance = balance + 100 WHERE name = 'Bob';` 语句时发生了错误，那么会使用 `ROLLBACK TO SAVEPOINT sp1;` 命令回滚到保存点 `sp1`，而不是回滚整个事务。这样 Alice 的账户余额已经减少 100 了，Bob 的账户余额也不会改变。

另外，在 MySQL 中，也可以使用 `SET autocommit = 0;` 命令来关闭自动提交，这样所有语句都必须显式地提交或回滚才能生效。例如：

```mysql
SET autocommit = 0;

UPDATE accounts SET balance = balance - 100 WHERE name = 'Alice';
UPDATE accounts SET balance = balance + 100 WHERE name = 'Bob';

COMMIT;
```

在这种情况下，如果要回滚事务，可以使用 `ROLLBACK` 命令，例如 `ROLLBACK;`。

总之，MySQL 事务是一种重要的机制，可以保证数据的完整性和一致性，提高系统的可靠性和可用性。

 

### mysql 表类型和存储引擎

![image-20221225205815687](https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221225205815687.png)

主要的存储引擎/表类型特点

![image-20221225205829107](https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221225205829107.png)



### MyISAM、InnoDB、MEMORY

![image-20221225205857641](https://typora-1257023524.cos.ap-shanghai.myqcloud.com/typora/image-20221225205857641.png)



```mysql
-- 表类型和存储引擎

-- 查看所有的存储引擎
SHOW ENGINES
-- innodb 存储引擎，是前面使用过.
-- 1. 支持事务 2. 支持外键 3. 支持行级锁

-- myisam 存储引擎
CREATE TABLE t28 (
	id INT,
	`name` VARCHAR(32)) ENGINE MYISAM
-- 1. 添加速度快 2. 不支持外键和事务 3. 支持表级锁

START TRANSACTION;
SAVEPOINT t1
INSERT INTO t28 VALUES(1, 'jack');
SELECT * FROM t28;
ROLLBACK TO t1

-- memory 存储引擎
-- 1. 数据存储在内存中[关闭了Mysql服务，数据丢失, 但是表结构还在] 
-- 2. 执行速度很快(没有IO读写) 3. 默认支持索引(hash表)

CREATE TABLE t29 (
	id INT,
	`name` VARCHAR(32)) ENGINE MEMORY
DESC t29
INSERT INTO t29
	VALUES(1,'tom'), (2,'jack'), (3, 'hsp');
SELECT * FROM t29

-- 指令修改存储引擎
ALTER TABLE `t29` ENGINE = INNODB
```

