create table post (
	post_id int auto_increment,
    title varchar(128),
    media_type varchar(128),
    pic mediumblob,
    
    primary key (post_id)
);