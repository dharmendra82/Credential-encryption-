#!/bin/bash

# Generate a unique subscript (salt)
salt=$(openssl rand -hex 16)

# Get the password to encrypt
password="mypassword"

# Generate a secret key
secret_key=$(openssl enc -aes-256-cbc -k secret -P | grep key | awk '{print $3}')

# Encrypt the password
encrypted_password=$(echo "$password" | openssl enc -aes-256-cbc -k "$secret_key" -S "$salt" -base64)

# Store the encrypted password, salt, and IV for later use
# You can use a database or a file to store these values
# ...

# To decrypt the password:

# Get the encrypted password, salt, and IV from storage
# You can use a database or a file to retrieve these values
encrypted_password=$(...)
salt=$(...)
iv=$(...)

# Decrypt the password
decrypted_password=$(echo "$encrypted_password" | openssl enc -aes-256-cbc -d -k "$secret_key" -S "$salt" -base64)

# Print the decrypted password
echo "$decrypted_password"
