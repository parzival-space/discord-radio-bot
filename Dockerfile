# this project was built using node v14.17.4, so we need to install it
FROM node:18.12.1

# set workdir
WORKDIR /app
COPY . .

# update npm
RUN npm install -g npm

# install dependencies
RUN npm install

# launch the app
CMD ["npm", "start"]