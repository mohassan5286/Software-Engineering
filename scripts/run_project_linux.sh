#!/bin/bash

# Start frontend in the background
echo "Starting frontend..."
(cd ../frontend && npm install && npm start) &

# Start backend
echo "Starting backend..."
(cd ../backend && java -jar backend.jar)
