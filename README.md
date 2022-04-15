# Getting Started

This repo contains React and Java Spring as Backend

## Setting up Frondend React

Open CMD, Navigate into front folder, Run

### `npm install` or `yarn install`

## Start Frontend React

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

## Setting up server

Open CMD, Navigate into server folder, Run

### `./mvnw spring-boot:run` for linux/mac
### `mvnw spring-boot:run` for windows

## Start Mongo server using docker

Navigate into folder contains docker-compose.db.yml file, Run

### `docker compose -f docker-compose.db.yml up -d`

## Using Docker compose

Navigate into folder contains docker-compose.yml file, Run

### `docker compose up -d --build`


