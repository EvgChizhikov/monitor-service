# Endpoints monitoring service

This service allows you  to monitor particular http/https URLs.

# How to run

1. Make sure 3306, 8080 ports is free.
2. Clone the project ```git clone https://github.com/EvgChizhikov/monitor-service.git```
3. In a root folder of project run command ```docker-compose up -d```

# How to use

1. After the application starts, you will have two users in the database with credentials:
```username: Batman, email: batman@example.com```
 ```username: Robin, email: robin@example.com```
First of all you have to make authentication with sending login in a header (POSTMAN collection is below), 
after that user will get access token what will allow you to make other requests.
2. In all next requests (except adding a new user) you have to send access token in a header for passing the authorization.

## API 

The application will start on http://localhost:8080/

|      <b>Address</b>     | <b>Method</b> |                 <b>Description</b>                       |
|:-----------------------:|:------:|:---------------------------------------------------------------:|
|   `/login`                   |  POST  |       User authorization. Sets access token.               |
|   `/api/users`               |  GET   |       Returns list of all users                            |
|   `/api/createUser`          |  POST  |       Creating user                                        |
|   `/api/addEndpoint`         |  POST  |       Adding a monitored endpoint to a user                |
|   `/api/deleteEndpoint`      |  POST  |       Removing monitored endpoint from a user.             |
|   `/api/update/{endpoint}`   |  POST  |       Updating monitored endpoint from a user.             |
|   `/api/results/{endpoint}`  |  GET   |       Getting all results from monitored endpoint.         |
|   `/api/endpoints`           |  GET   |       Getting all monitored endpoints of user.             |

## Postman collection
You can import this collection to POSTMAN with choosing "Import" option in a top left corner of POSTMAN
```
{
	"info": {
		"_postman_id": "97aea30d-f95b-4e3f-b675-55c747624a6a",
		"name": "monitor-service collection",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
	},
	"item": [
		{
			"name": "login",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "username",
						"value": "Batman",
						"type": "text"
					},
					{
						"key": "email",
						"value": "batman@example.com",
						"type": "text"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/login",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"login"
					]
				}
			},
			"response": []
		},
		{
			"name": "getUsers",
			"protocolProfileBehavior": {
				"disableBodyPruning": true
			},
			"request": {
				"method": "GET",
				"header": [
					{
						"key": "Content-Type",
						"value": "application/json",
						"type": "text"
					},
					{
						"key": "accessToken",
						"value": "ae95c462-c1c1-4603-9653-19fe914013e3",
						"type": "text"
					}
				],
				"body": {
					"mode": "raw",
					"raw": ""
				},
				"url": {
					"raw": "http://localhost:8080/api/users",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"users"
					]
				}
			},
			"response": []
		},
		{
			"name": "Add User",
			"request": {
				"method": "POST",
				"header": [],
				"body": {
					"mode": "raw",
					"raw": "{\r\n  \"userName\": \"Robin\",\r\n  \"email\": \"robin@example.com\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/createUser",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"createUser"
					]
				}
			},
			"response": []
		},
		{
			"name": "Add Endpoint to User",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "accessToken",
						"value": "ae95c462-c1c1-4603-9653-19fe914013e3",
						"type": "text"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\r\n  \"endpointName\": \"google\",\r\n  \"url\": \"https://www.google.com/\",\r\n  \"monitoredInterval\": 5\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/addEndpoint",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"addEndpoint"
					]
				}
			},
			"response": []
		},
		{
			"name": "Delete Endpoint",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "accessToken",
						"value": "ae95c462-c1c1-4603-9653-19fe914013e3",
						"type": "text"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\r\n  \"endpointName\": \"google\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/deleteEndpoint",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"deleteEndpoint"
					]
				}
			},
			"response": []
		},
		{
			"name": "Update Endpoint",
			"request": {
				"method": "POST",
				"header": [
					{
						"key": "accessToken",
						"value": "b7bae777-8096-4723-b381-8a3141a94630",
						"type": "text"
					}
				],
				"body": {
					"mode": "raw",
					"raw": "{\r\n    \"endpointName\": \"youtube\",\r\n    \"monitoredInterval\": 10,\r\n    \"url\": \"https://www.youtube.com/\"\r\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				},
				"url": {
					"raw": "http://localhost:8080/api/update/google",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"update",
						"google"
					]
				}
			},
			"response": []
		},
		{
			"name": "Get Results",
			"request": {
				"method": "GET",
				"header": [
					{
						"key": "accessToken",
						"value": "ae95c462-c1c1-4603-9653-19fe914013e3",
						"type": "text"
					}
				],
				"url": {
					"raw": "http://localhost:8080/api/results/google/",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"results",
						"google",
						""
					]
				}
			},
			"response": []
		},
		{
			"name": "Get Endpoints",
			"request": {
				"method": "GET",
				"header": [
					{
						"key": "accessToken",
						"value": "4fad212d-75d8-4b9a-a4f0-faa96e0b2a6b",
						"type": "text"
					}
				],
				"url": {
					"raw": "http://localhost:8080/api/endpoints",
					"protocol": "http",
					"host": [
						"localhost"
					],
					"port": "8080",
					"path": [
						"api",
						"endpoints"
					]
				}
			},
			"response": []
		}
	]
}
```