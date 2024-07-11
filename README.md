# Online BCRYPT Generator App




## Example of using
![Logo](https://i.imgur.com/ewTt4Wt.png)

You just need to type your text, choose cost factor (or salt) and click button to generate your hash.


## Tech Stack

**Client:** React, TypeScript, HTML, CSS

**Server:** Java 21, Spring Boot 3, Spring Security, BCrypt

**Infrastructure:** Docker, docker-compose



## Build and run locally

You need to have intalled Docker!

Clone this project to your directory:
```
git clone https://github.com/ZQR0/online-bcrypt-generator.git
```

Then run:
```
cd client/hash-generator-client
docker build --tag hash-generator-client:latest .
```

Then run:
```
cd hashgenerator
docker build --tag hash-generator-backend:latest .
```

And finally go to root and run:

```bash
  docker-compose up
```
    
## Feedback

If you have any feedback, please reach out to me at Telegram @zqrodev
