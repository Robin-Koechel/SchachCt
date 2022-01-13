from msilib import _directories
from msilib.schema import Directory
import os

path = "F:\JAVA\Projekte\GUI\Schach\ProjektSchach\src\\"
filePaths=[]
linecount = 0
for root, directories, files in os.walk(path,topdown=False):
    for name in files:
        filePaths.append(os.path.join(root, name))
        print(os.path.join(root, name))
    for name in directories:
        filePaths.append(os.path.join(root, name))
        print(os.path.join(root, name))

del filePaths[-1]
for f in filePaths:
    print(f)
    file = open(f,"r", encoding="utf8")
    for line in file:
        linecount += 1
print("\n")
print(linecount)
