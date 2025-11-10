

import random, csv, re

# --- Config
RANDOM_SEED = 42
TARGET_ROWS = 300000
OUT_CSV = "synthetic_300000_pw_pairs_v2.csv"
random.seed(RANDOM_SEED)

# --- Seed lists
FIRSTS_BASE = ["alex","sam","jordan","taylor","morgan","casey","jamie","chris","drew","kai",
               "emily","olivia","liam","noah","ava","mia","sophia","lucas","ethan","amelia"]
LASTS_BASE = ["smith","johnson","lee","martin","brown","garcia","rodriguez","davis","miller","wilson",
              "moore","taylor","anderson","thomas","jackson","white","harris","martinez","clark","lewis"]
WORDS_BASE  = ["sun","moon","shadow","dragon","coffee","honey","purple","spring","blue","storm",
               "green","river","mount","fire","ember","stone","silk","ghost","pixel","vector",
               "nova","orbit","glow","cipher","rocket","delta","sigma","quant","matrix","ember"]

DOMAINS = ["gmail.com","yahoo.com","outlook.com","hotmail.com","protonmail.com","example.org"]

def expand_list(seed, target_min=200, decorators=None):
    out = list(seed)
    if decorators is None:
        decorators = ["", "1", "123", "007", "x", "_", ".", "42", "2020", "99", "pro"]
    i = 0
    while len(out) < target_min:
        a = random.choice(seed)
        b = random.choice(seed)
        dec = random.choice(decorators)
        if random.random() < 0.3:
            candidate = f"{a}{dec}{b[:3]}"
        elif random.random() < 0.6:
            candidate = f"{a}{dec}"
        else:
            candidate = f"{a}{b}{dec}"
        if candidate not in out:
            out.append(candidate)
        i += 1
        if i > target_min * 15:
            break
    caps = [s.capitalize() for s in out if s.islower()]
    out += caps[:max(0, target_min - len(out))]
    return list(dict.fromkeys(out))

FIRSTS = expand_list(FIRSTS_BASE, target_min=400)
LASTS  = expand_list(LASTS_BASE,  target_min=400)
COMMON_WORDS = expand_list(WORDS_BASE, target_min=1200, decorators=["","1","!","_","99","2021","_x","xyz"])

KEY_NEIGHBORS = {
    'a':'qsw', 's':'awed', 'd':'serfc', 'f':'drtg', 'g':'ftyhb',
    'h':'gyujn', 'j':'huikm', 'k':'jiolm', 'l':'kop', 'q':'wa',
    'w':'qase', 'e':'wsdr', 'r':'edfgt', 't':'rfgy', 'y':'tghu',
    'u':'yhji', 'i':'ujko', 'o':'iklp', 'p':'ol',
    '1':'2', '2':'13', '3':'24', '4':'35', '5':'46', '6':'57',
    '7':'68', '8':'79', '9':'8', '0':'9'
}

_token_re = re.compile(r'\d+|[A-Za-z]+|[^A-Za-z0-9]+')
def tokenize_pw(pw): return _token_re.findall(pw)
def join_chunks(chunks): return "".join(chunks)

# email generator
def make_email(first, last):
    pats = [
        f"{first}.{last}",
        f"{first}{last}",
        f"{first[0]}{last}",
        f"{first}{random.randint(1,99)}",
        f"{first}_{last}"
    ]
    return random.choice(pats) + "@" + random.choice(DOMAINS)

# sample chunk by "type"
def sample_chunk_of_type(chunk_type, first, last):
    if chunk_type == 'digit':
        return str(random.randint(10,9999))
    if chunk_type == 'letters':
        r = random.random()
        if r < 0.55:
            return random.choice(COMMON_WORDS)
        elif r < 0.85:
            return random.choice([first, last, first+last[:2]])
        else:
            # random short alpha
            l = random.randint(3,7)
            return ''.join(random.choice('abcdefghijklmnopqrstuvwxyz') for _ in range(l))
    return random.choice(['!','!!','@','#','$','_', '.'])

# small mutations
def neighbor_substitute(ch):
    low = ch.lower()
    if low in KEY_NEIGHBORS and KEY_NEIGHBORS[low]:
        return random.choice(KEY_NEIGHBORS[low])
    return ch

def apply_common_leet(s, prob=0.22):
    mapping = {'a':'@','s':'$','o':'0','i':'1','e':'3','t':'7'}
    out = ''.join(mapping.get(ch.lower(), ch) if (ch.isalpha() and random.random()<prob) else ch for ch in s)
    return out

def randomize_some_caps(s, p=0.18):
    lst = list(s)
    for i in range(len(lst)):
        if lst[i].isalpha() and random.random() < p:
            lst[i] = lst[i].upper()
    return ''.join(lst)

def mutate_chunk(chunk, first, last):
    if chunk.isdigit():
        if len(chunk) <= 2:
            return str(random.randint(10,99))
        return str((int(chunk) + random.randint(1,99)) % 9999)
    if chunk.isalpha():
        r = random.random()
        if r < 0.25:
            return chunk.capitalize()
        if r < 0.5:
            i = random.randrange(len(chunk))
            lst = list(chunk)
            lst[i] = neighbor_substitute(lst[i])
            return ''.join(lst)
        if r < 0.8:
            return chunk + str(random.randint(1,9))
        return random.choice(COMMON_WORDS)
    return random.choice(['!','@','#','_','--'])

# build password1 from templates
def build_base_password(first, last):
    templates = [
        ("letters","digit"),
        ("letters","letters"),
        ("letters","symbol"),
        ("letters","digit","symbol"),
        ("letters","letters","digit"),
        ("letters","letters","symbol"),
        ("letters","digit","digit"),
        ("letters","symbol","digit"),
        ("letters",),
        ("letters","digit","letters")
    ]
    tpl = random.choice(templates)
    parts = [ sample_chunk_of_type(t, first, last) for t in tpl ]
    pw = join_chunks(parts)
    if random.random() < 0.25:
        pw = apply_common_leet(pw)
    if random.random() < 0.30:
        pw = randomize_some_caps(pw)
    return pw

def produce_similar_password(base_pw, first, last):
    chunks = tokenize_pw(base_pw)
    n = len(chunks)
    keep_count = 1 if (n == 1 or random.random() < 0.6) else 2
    keep_count = min(keep_count, n)
    keep_idx = sorted(random.sample(range(n), keep_count))
    new_chunks = []
    for i,ch in enumerate(chunks):
        if i in keep_idx:
            if random.random() < 0.12:
                new_chunks.append(mutate_chunk(ch, first, last))
            else:
                new_chunks.append(ch)
        else:
            if random.random() < 0.5:
                new_chunks.append(mutate_chunk(ch, first, last))
            else:
                if ch.isdigit():
                    new_chunks.append(sample_chunk_of_type('digit', first, last))
                elif ch.isalpha():
                    new_chunks.append(sample_chunk_of_type('letters', first, last))
                else:
                    new_chunks.append(sample_chunk_of_type('symbol', first, last))

    if random.random() < 0.08:

        new_chunks.append(random.choice(['1','!','99']))
    candidate = join_chunks(new_chunks)

    if random.random() < 0.18:
        candidate = apply_common_leet(candidate, prob=0.18)
    if random.random() < 0.22:
        candidate = randomize_some_caps(candidate, p=0.12)

    if candidate == base_pw:
        candidate = candidate + str(random.randint(1,9))
    return candidate


def generate_pairs(n=TARGET_ROWS):
    seen_usernames = set()
    records = []
    attempts = 0
    while len(records) < n:
        attempts += 1

        first = random.choice(FIRSTS)
        last = random.choice(LASTS)
        uname_patterns = [
            f"{first}.{last}",
            f"{first}{last}",
            f"{first}{random.randint(1,99)}",
            f"{first}_{last}",
            f"{first[0]}{last}{random.randint(1,99)}",
            f"{first}{last}{random.choice(['','1','99','x'])}"
        ]
        username = random.choice(uname_patterns).lower()
        if username in seen_usernames:
            username = f"{username}{random.randint(1,999)}"
        if username in seen_usernames:
            continue
        seen_usernames.add(username)
        email = make_email(first, last).lower()

        pw1 = build_base_password(first, last)
        pw2 = produce_similar_password(pw1, first, last)
        if pw1 == pw2:
            pw2 = pw2 + random.choice(['1','!','99'])
        records.append((username, email, pw1, pw2))
        if attempts > n * 10 and len(records) < n:
            raise RuntimeError("Too many attempts generating unique usernames; increase seed or corpora.")
    return records

def write_csv(records, path=OUT_CSV):
    fieldnames = ['record_id','username','email','password1','password2']
    with open(path, 'w', newline='', encoding='utf-8') as f:
        w = csv.writer(f)
        w.writerow(fieldnames)
        for i, r in enumerate(records):
            w.writerow([i, r[0], r[1], r[2], r[3]])

if __name__ == "__main__":
    rows = generate_pairs(TARGET_ROWS)
    write_csv(rows, OUT_CSV)
    print(f"Wrote {len(rows)} records to {OUT_CSV}")
