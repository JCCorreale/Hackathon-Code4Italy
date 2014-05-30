This project comes with a build system script (gulpfile.js) suited to deploy automatically the web interface

# installation
1. install NodeJs
2. open a node terminal
3. go into the project root (this folder)
4. run 'npm install'
5. copy the folder in node_modules-patches into node_modules (will overwrite 2 files in the gulp-ftp dependency)

# usage
The build system script offers different tasks:

'gulp deploy-all', deploys the whole website (folder web/deploy)

'gulp deploy-data', deploys only the data (folder web/deploy/data) 

*Warning*: the console output is missing, the ftp process will write a message only at the end of the trasmission (the terminal seems to freeze for a minute but it's still working)