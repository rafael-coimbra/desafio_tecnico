Desafio Técnico - Rafael Coimbra Gus

## DATA ANALYSIS SYSTEM ##

# How to use the application:

1) Clone this repository;

2) Run the bash file 'setup_and_run.sh'. To do it, simply run the commands 'chmod +x setup_and_run.sh' and '$ ./setup_and_run.sh' in your terminal;

3) The application will start running. If you want to stop it, press 'Ctrl+C' in your terminal.

## Watch out! ##

# 1) The separation between datas is a 'ç'. If you have to write some word which contains this character, right with an 'c' instead. 

E.g.:
Wrong input:
001çCPFçLourenço da SilvaçSalary

How you should write:
001çCPFçLourenco da SilvaçSalary

# 2) Write only one complete data in each line.

E.g.:
Wrong input:
001çCPFçNameçSalary 001çCPFçNameçSalary

How you should write:
001çCPFçNameçSalary
001çCPFçNameçSalary

# 3) To keep the application working and running correctly, you have to follow the rules on how to write the data in each file. If you type something wrong, e.g. a String where you should have written just numbers, the application will stop and launch an Exception explaining what happened, in which line and file. Fix it and then run the bash file again.

# 4) If you write duplicated datas, such as multiple inputs with the same CPF/CNPJ/sale ID, the application will also stop and launch an Exception.

