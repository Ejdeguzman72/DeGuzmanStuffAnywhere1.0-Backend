
-- adding foreign keys for auto_transactions table
alter table auto_transactions 
add foreign key (car_id) references car(car_id);

alter table auto_transactions 
add foreign key (person_id) references person(person_id);

alter table auto_transactions 
add foreign key (transaction_type_id) references transaction_type(transaction_type_id);

-- adding foreign key for general_transaction table
alter table general_transaction 
add foreign key (person_id) references person(person_id);

alter table general_transaction 
add foreign key (transaction_type_id) references transaction_type(transaction_type_id);

-- adding foreign key for medical_transactions table 
alter table medical_transactions 
add foreign key (facility) references facility(facility_id);

alter table medical_transactions 
add foreign key (person_id) references person(person_id);

alter table medical_transactions 
add foreign key (transaction_type_id) references transaction_type(transaction_type_id);

-- adding foreign keys for recipe table
alter table recipe 
add foreign key (recipe_type_id) references recipe_type(recipe_type_id);

-- adding foreign keys for restaurant
alter table restaurant 
add foreign key (restaurant_type_id) references restaurant_type(restaurant_type_id);

-- adding foreign keys for run_tracker table 
alter table run_tracker 
add foreign key (person_id) references person(person_id);

-- adding foreign key for users table 
alter table users 
add foreign key (role_id) references role(role_id);

alter table users 
add foreign key (user_status_id) references user_status(user_status_id);

-- adding foreign keys for utility table 
alter table utility 
add foreign key (utility_type_id) references utility_type(utility_type_id);
