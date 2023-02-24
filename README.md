# tejal_ipranges

Viewing all the IP Ranges for selected region from AWS IP ranges

in order to create IP filter on security groups.


## API Reference

#### Get selectable IP Ranges

```http
  GET /ipranges?region=
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `region` | `String`  | The region for which you wish to view IP Ranges| 

The valid regions are  EU, US, AP, CN, SA, AF, CA
The ALL query parameter will return all available the IP Ranges.


## Deployment

The application is Dockerized.

Docker Image name: tejal_ipranges_otto_jib

Run the following commands to run the application:

docker pull tejalaher7/tejal_ipranges_otto_jib

```bash

  docker pull tejalaher7/tejal_ipranges_otto_jib

  docker run -p 8080:8080 tejal_ipranges_otto_jib

```
The Application should run on port 8080.

Example URL for localhost

http://localhost:8080/ipranges?region=US
