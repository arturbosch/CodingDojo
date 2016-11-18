from urllib.request import Request, urlopen


def http_get(url):
    request = Request(url)
    request.add_header("User-Agent", "Mozilla/5.0")
    return urlopen(request).read()


content = http_get("https://www.google.de/search?q=google")
print(content)
