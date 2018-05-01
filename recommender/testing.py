import requests
import json
import goslate
from recommend_scrappy.recommend_scrappy.spiders.coursera_scrapy import CourseraSpider
from topic_modeling import CourseraTopicModeler
from scrapy.crawler import CrawlerProcess
from pathlib import Path


if __name__ == '__main__':
    # process = CrawlerProcess({})
    # modeler = CourseraTopicModeler()
    #
    # text = 'машинне навчання'
    # input_text = modeler.translate(text)
    # search_page = modeler.create_url(input_text)
    # process.crawl(CourseraSpider, search_page=search_page)
    # process.start()

    url = 'http://0.0.0.0:5000/recommend'
    input_data = {'title': 'it works'}
    result = requests.post(url, json=input_data).json()
    print(result)