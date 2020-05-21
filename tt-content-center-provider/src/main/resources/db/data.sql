
CREATE TABLE IF NOT EXISTS hotwordcount_hotword_relation(
    id varcahr(32) not null primary key,    hot_word_count_id varchar(255) not null ,    hot_word_id varchar(255) not null ,    tenant_code varchar(255) not null ,    created_at datetime not null ,    created_by varchar(255) not null ,    updated_at datetime not null ,    updated_by varchar(255) not null ,    version int  not null ,    dr int  not null );
CREATE TABLE IF NOT EXISTS floor_page(
    id varcahr(32) not null primary key,    name varchar(255)  ,    status int   ,    img_url varchar(255)  ,    tenant_code varchar(255)  ,    created_at datetime  ,    created_by varchar(255)  ,    updated_at datetime  ,    updated_by varchar(255)  ,    version int   ,    dr tinyint  );
CREATE TABLE IF NOT EXISTS hot_word(
    id varcahr(32) not null primary key,    name varchar(255) not null ,    search_num int  not null ,    source varchar(255) not null ,    tag_red int  not null ,    tenant_code varchar(255) not null ,    created_at datetime not null ,    created_by varchar(255) not null ,    updated_at datetime not null ,    updated_by varchar(255) not null ,    version int  not null ,    dr int  not null );
CREATE TABLE IF NOT EXISTS floor_goods_relation(
    id varcahr(32) not null primary key,    floor_id varchar(255)  ,    goods_id varchar(255)  ,    weight int   ,    tenant_code varchar(255)  ,    created_at datetime  ,    created_by varchar(255)  ,    updated_at datetime  ,    updated_by varchar(255)  ,    version int   ,    dr tinyint  );
CREATE TABLE IF NOT EXISTS interest(
    id varcahr(32) not null primary key,    interest_name varchar(255) not null ,    interest_type varchar(255) not null ,    interest_value varchar(255) not null ,    status int  not null ,    level int  not null ,    guide_name varchar(255) not null ,    tenant_code varchar(255) not null ,    created_at datetime not null ,    created_by varchar(255) not null ,    updated_at datetime not null ,    updated_by varchar(255) not null ,    version int  not null ,    dr int  not null );
CREATE TABLE IF NOT EXISTS banner_set(
    id varcahr(32) not null primary key,    name varchar(255)  ,    img_url varchar(255)  ,    start_time datetime  ,    end_time datetime  ,    status int   ,    weight int   ,    tenant_code varchar(255)  ,    created_at datetime  ,    created_by varchar(255)  ,    updated_at datetime  ,    updated_by varchar(255)  ,    version int   ,    dr tinyint  );
CREATE TABLE IF NOT EXISTS popup_manage(
    id varcahr(32) not null primary key,    name varchar(255)  ,    details varchar(255)  ,    status int   ,    img_url varchar(255)  ,    member_type tinyint  ,    member_level_id varchar(255)  ,    member_group_id varchar(255)  ,    popup_type tinyint  ,    page_id varchar(255)  ,    page_name varchar(255)  ,    belong_application varchar(255)  ,    trigger_event int   ,    tenant_code varchar(255)  ,    created_at datetime  ,    created_by varchar(255)  ,    updated_at datetime  ,    updated_by varchar(255)  ,    version int   ,    dr tinyint  );
CREATE TABLE IF NOT EXISTS url(
    id varcahr(32) not null primary key,    url varchar(255)  ,    tenant_code varchar(255)  ,    created_at datetime  ,    created_by varchar(255)  ,    updated_at datetime  ,    updated_by varchar(255)  ,    version int   ,    dr tinyint  );
CREATE TABLE IF NOT EXISTS banner_manage(
    id varcahr(32) not null primary key,    site varchar(255)  ,    amout int   ,    application_of_affiliation varchar(255)  ,    tenant_code varchar(255)  ,    created_at datetime  ,    created_by varchar(255)  ,    updated_at datetime  ,    updated_by varchar(255)  ,    version int   ,    dr tinyint  );
CREATE TABLE IF NOT EXISTS project_page(
    id varcahr(32) not null primary key,    name varchar(255)  ,    status int   ,    img_url varchar(255)  ,    tenant_code varchar(255)  ,    created_at datetime  ,    created_by varchar(255)  ,    updated_at datetime  ,    updated_by varchar(255)  ,    version int   ,    dr tinyint  );
CREATE TABLE IF NOT EXISTS interest_member_relation(
    id varcahr(32) not null primary key,    interest_id varchar(255) not null ,    member_information_id varchar(255) not null ,    tenant_code varchar(255) not null ,    created_at datetime not null ,    created_by varchar(255) not null ,    updated_at datetime not null ,    updated_by varchar(255) not null ,    version int  not null ,    dr int  not null );
CREATE TABLE IF NOT EXISTS manage_set_relation(
    id varcahr(32) not null primary key,    manage_id varchar(255)  ,    set_id varchar(255)  ,    tenant_code varchar(255)  ,    created_at datetime  ,    created_by varchar(255)  ,    updated_at datetime  ,    updated_by varchar(255)  ,    version int   ,    dr tinyint  );
CREATE TABLE IF NOT EXISTS div_url(
    id varcahr(32) not null primary key,    div_url varchar(255)  ,    tenant_code varchar(255)  ,    created_at datetime  ,    created_by varchar(255)  ,    updated_at datetime  ,    updated_by varchar(255)  ,    version int   ,    dr tinyint  );
CREATE TABLE IF NOT EXISTS advert_content_relation(
    id varcahr(32) not null primary key,    set_type int   ,    set_type_id varchar(255)  ,    advert_type int   ,    advert_id varchar(255)  ,    weight int   ,    tenant_code varchar(255)  ,    created_at datetime  ,    created_by varchar(255)  ,    updated_at datetime  ,    updated_by varchar(255)  ,    version int   ,    dr tinyint  );
CREATE TABLE IF NOT EXISTS hotword_goodslables_relation(
    id varcahr(32) not null primary key,    hot_word_id varchar(255) not null ,    goods_labels_id varchar(255) not null ,    tenant_code varchar(255) not null ,    created_at datetime not null ,    created_by varchar(255) not null ,    updated_at datetime not null ,    updated_by varchar(255) not null ,    version int  not null ,    dr int  not null );
CREATE TABLE IF NOT EXISTS hot_word_count(
    id varcahr(32) not null primary key,    on_show int  not null ,    search_num int  not null ,    hot_words varchar(255) not null ,    show_date date not null ,    tenant_code varchar(255) not null ,    created_at datetime not null ,    created_by varchar(255) not null ,    updated_at datetime not null ,    updated_by varchar(255) not null ,    version int  not null ,    dr int  not null );
