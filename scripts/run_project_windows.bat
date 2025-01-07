#!/bin/bash

# Check for Rosetta and prompt to install if needed
if [[ $(uname -m) == "arm64" ]]; then
    echo "You are using an Apple Silicon Mac. Checking for Rosetta..."
    if ! /usr/bin/pgrep oahd >/dev/null 2>&1; then
        echo "Rosetta is not installed. Installing..."
        softwareupdate --install-rosetta --agree-to-license
    fi
fi

# Start frontend in the background
echo "Starting frontend..."
(cd ../frontend && npm install && npm start) &

# Start backend
echo "Starting backend..."
(cd ../backend && java -jar backend.jar)
