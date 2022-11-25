# this project was built using node v14.17.4, so we need to install it
FROM node:14.17.4

# set workdir
WORKDIR /app
COPY . .

# install dependencies
RUN npm ci

# launch the app
CMD ["npm", "start"]