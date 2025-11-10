import csv
from collections import defaultdict

INPUT_FILE  = "CraftRise2024.txt"
OUTPUT_FILE = "CraftRiseGrouped.txt" # grouped output

def group_by_email(infile=INPUT_FILE, outfile=OUTPUT_FILE):
    groups = defaultdict(list)

    # Read & group
    with open(infile, "r", encoding="utf-8", newline="") as f:
        reader = csv.reader(f, delimiter="\t")
        for row in reader:
            if not row or len(row) < 1:
                continue
            email = row[0].strip().strip('"')
            groups[email].append(row)

    with open(outfile, "w", encoding="utf-8", newline="") as f:
        writer = csv.writer(f, delimiter="\t", quoting=csv.QUOTE_MINIMAL)
        for email in sorted(groups.keys()):
            for row in groups[email]:
                writer.writerow(row)

    print(f"Done. Grouped {len(groups)} unique emails. Output → {outfile}")

if __name__ == "__main__":
    group_by_email()
