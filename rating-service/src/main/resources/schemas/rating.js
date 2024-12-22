use reviewSystemDB;

db.createUser({user: "wulf", pwd: "wulf123", roles: [{role: "readWrite", db: "reviewSystemDB"}]});
db.auth("wulf", "wulf");