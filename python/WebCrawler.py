from bs4 import BeautifulSoup
import re
import urllib.request
import urllib.error
import time


# local_filename, headers = urllib.request.urlretrieve('', 'image.jpg')

# start = 'http://' + input("Where would you like to start searching?\n")

def download(html, base, fileType, fileList):
    soup = BeautifulSoup(html, "html.parser")
    for link in soup.find_all('a'):
        fileName = str(link.get('href'))
        print(fileName)
        if fileType in fileName:
            linkGet = base + fileName
            fileSave = fileName.replace('/', '')
            try:
                urllib.request.urlretrieve(linkGet, 'pics/' + fileSave)
            except urllib.error.HTTPError:
                print("Error for " + linkGet)
        elif "htm" in fileName:
            fileList.append(link)


start = 'http://www.irrelevantcheetah.com/browserimages.html'
base = "http://www.irrelevantcheetah.com"

fileType = input("What file type are you looking for?\n")
html = urllib.request.urlopen(start)

linkList = []

print("Parsing " + start)
download(html, base, fileType, linkList)

for leftOver in linkList:
    time.sleep(0.1)
    linkText = base + str(leftOver.get('href'))
    print("Parsing " + linkText)
    html = urllib.request.urlopen(linkText)
    linkList = []
    download(html, base, fileType, linkList)
