## API basics

All API communications is done with JSON format, typical response format will contain at least 2 fields, one of them will be "status" which will denote is request success or error and other one willbe "message" contaning user readable information about response, "status" zero will indicate request success while all other values will map to one of possible errors, for example:

Success response:
```
{
	"status": 0,
	"message": "OK"
}
```
Error response:
```
{
	"status": 1,
	"message": "Bad input parameters"
}
```

API responses that contain data should have additional data field, for example:

```
{
	"status": 0,
	"message": "OK",
	"data": {
		"user_id": 1,
		"user_name": "Letizia"
	}
}
```

## List of provided routes

### Send firebase registration token

Path: token/

Method: POST

Request example:

```
{
	"user": "61",
	"token": "2313dsda"
}
```

Response example:

```
{
	"status": 0,
	"message": "OK"
}
```

### Send notification answer

Path: answer/

Method: POST

Request example:

```
{
	"user": "61",
	"question": 123,
	"answer": 1
}
```

Response example:

```
{
	"status": 0,
	"message": "OK"
}
```