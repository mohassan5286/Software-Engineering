@echo off

:: Start frontend in a new command prompt
echo Starting frontend...
start cmd.exe /k "cd ../frontend && npm install && npm start"

:: Start backend in the current prompt
echo Starting backend...
cd ../backend
java -jar backend.jar
pause
