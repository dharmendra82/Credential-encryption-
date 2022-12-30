#key="1234567812345678"
#iv="1234567812345678"
password="pass!3234542"

# Set the string to encrypt
string="test@1Test"

# Encrypt the string using openssl
# bencrypted_string=$(echo "$string" | openssl enc -aes-256-cbc -k "$key" -iv "$iv" -base64)

encrypted_string=$(echo "$string" | openssl enc -aes-256-cbc -k "$password" -pbkdf2 -base64)

# Print the encrypted string
echo $encrypted_string

echo "$encrypted_string" | openssl enc -aes-256-cbc -d -k "$password" -pbkdf2 -base64 > test.txt

