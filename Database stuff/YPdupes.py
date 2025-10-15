
import re
from collections import defaultdict

input_file = "YPLeaks.txt"
output_file = "Ypduplicates.txt"

email_passwords = defaultdict(list)

with open(input_file, "r", encoding="utf-8") as f:
    content = f.read()

entries = re.findall(r"username=([^\s]+).*?email=([^\s]+).*?password=([^\s]+)", content, flags=re.DOTALL)

for username, email, password in entries:
    email_passwords[email.strip()].append(password.strip())

# Find duplicates
duplicates = {email: pw_list for email, pw_list in email_passwords.items() if len(pw_list) > 1}

# Write to file
with open(output_file, "w", encoding="utf-8") as f:
    for email, pw_list in duplicates.items():
        for pw in pw_list:
            f.write(f"{email}\t{pw}\n")

print(f"Found {len(duplicates)} duplicate emails. Saved to {output_file}")
