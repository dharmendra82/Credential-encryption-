Copy code
# Encrypt the password using OpenSSL and a secret key
password="mypassword"
secret_key="mykey"
encrypted_password=$(echo -n $password | openssl enc -aes-256-cbc -a -K $secret_key)

# Store the encrypted password in a file
echo $encrypted_password > encrypted_password.txt
