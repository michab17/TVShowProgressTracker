-- User Table
	-- id
    -- username unique
    -- password
    -- role

-- Joins table
	-- user_id
    -- tv show id
    -- current episode
    -- rating (out of 5)

-- TV show Table
	-- id
    -- name
    -- description
	-- total # of episodes
    
create database new_progress_tracker;
use new_progress_tracker;
    
create table user(
	user_id int primary key auto_increment,
    username varchar(20) unique not null,
    password varchar(25) not null,
    role varchar(15)
);

create table tv_show(
	show_id int primary key auto_increment,
    name varchar(50) unique not null,
	description text(1000),
    episode_count int not null
);

create table user_show (
	user_id int,
    show_id int,
    primary key(user_id, show_id),
    foreign key (user_id) references user(user_id) on delete cascade,
    foreign key (show_id) references tv_show(show_id) on delete cascade,
    current_episode int not null default 0,
    rating int
);

select * from user;
select * from tv_show;
select* from user_show;

insert into user values(null, "superuser", "password", "super");
insert into user values(null, "micha", "password", "admin");
insert into user values(null, "eric", "eric@123", "admin");
insert into user values(null, "travis", "password", "admin");
insert into user values(null, "user", "password", "user");

insert into user values
	(null, "rachel", "rachel@123", "user"),
	(null, "michael", "micahel@123", "user"),
	(null, "luke", "luke@123", "user"),
	(null, "noah", "noah@123", "user"),
	(null, "olivia", "olivia@123", "user");

insert into tv_show values(null, "Breaking Bad", null, 62);
insert into tv_show values(null, "The 100", null, 70);
insert into tv_show values(null, "Better Call Saul", null, 63);
insert into tv_show values(null, "Queens Gambit", null, 8);
insert into tv_show values(null, "Avatar: The Last Airbender", null, 61);

insert into user_show values(7, 1, 38, null);
insert into user_show values(8, 1, 45, null);
insert into user_show values(9, 1, 70, 4);
insert into user_show values(10, 1, 59, null);
insert into user_show values(11, 1, 0, null);

select * from user u join user_show us on u.user_id = us.user_id join tv_show ts on us.show_id = ts.show_id;

select * from user_show;

select avg(rating) 
from User_Show us join TV_show ts on us.show_id = ts.show_id
-- where ts.name = "Breaking Bad"
group by us.show_id;

use new_progress_tracker;

select name, avg(rating) as show_average  from User_Show us join tv_show ts on us.show_id = ts.show_id group by us.show_id;



