alter table user_person 
add foreign key (personid) references person(personid);

alter table general_transaction
add foreign key (transaction_type) references transaction_type(transaction_type_id);

alter table medical_transaction 
add foreign key (transaction_type) references transaction_type(transaction_type_id);

alter table auto_transcation
add foreign key (transcation_type) references transaction_type(transaction_type_id);


