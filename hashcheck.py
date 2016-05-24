# -*- coding: utf-8 -*-
import hashlib
import os

root_path = './src/'
files = os.listdir(root_path)

checks = {}
checkfiles = ["/Card.png"]

for imgfile_name in checkfiles:
    print('# ' + imgfile_name)
    print(' ## exists check:')
    for sid in files:
        path = root_path + sid + imgfile_name
        try:
            with open(path, mode='rb') as f:
                data = f.read()
                m = hashlib.md5(data).hexdigest()
                # print(sid + ": " + m)
                if m not in checks:
                    checks[m] = []
                checks[m].append(sid)
        except FileNotFoundError:
            other = [fn for fn in os.listdir(root_path + sid) if '.png' in fn]
            print('  ' + sid + ': no file, or ' + str(other))

    copy_pairs = []
    for hash, ids in checks.items():
        if len(ids) >= 2:
            copy_pairs.append(ids)

    print(' ## copies: ')
    print('  ' + str(copy_pairs))
    print('  count: ' + str(len(copy_pairs)))
    print()
