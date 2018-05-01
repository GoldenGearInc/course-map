import scrapy
import json
from scrapy.crawler import CrawlerProcess


class CourseraSpider(scrapy.Spider):
    name = "coursera"
    start_urls = []

    def __init__(self, search_page=None, storage=None):
        scrapy.Spider.__init__(self)
        self.search_page = search_page
        self.storage = storage
        CourseraSpider.start_urls.append(self.search_page)

    def parse(self, response):
        url_num = 5
        recommended_info = []

        for i in range(url_num):
            course_index = 5 + i
            url_xpath = f'//*[@id="rendered-content"]/div/div/div[2]/div[1]/div[2]/div[1]/div[{course_index}]/a'
            title_xpath = f'//*[@id="rendered-content"]/div/div/div[2]/div[1]/div[2]/div[1]/div[{course_index}]/a' \
                          f'/div[1]/div/div[2]/div[1]/h2/text()'
            # img_xpath = f'//*[@id="rendered-content"]/div/div/div[2]/div[1]/div[2]/div[1]' \
            #             f'/div[{course_index - 1}]/a/div[1]/div/div[1]/img'

            url = response.xpath(url_xpath).extract_first()
            url = Preprocessor.find_href(url)
            title = response.xpath(title_xpath).extract_first()
            # image = response.xpath(img_xpath).extract_first()
            course_info = {'title': title, 'url': url}
            recommended_info.append(course_info)

        with open(self.storage, 'w') as f:
            f.write(json.dumps({'recommended_info': recommended_info}, indent=4))
        yield {
            'recommended_info': recommended_info
        }


class Preprocessor:
    @staticmethod
    def find_href(input_query):
        url = []
        index = input_query.find('href')
        cropped = input_query[(index + 7):]
        for symbol in cropped:
            if symbol == '"':
                break
            url.append(symbol)
        url = ''.join(url)
        full_url = ''.join(['https://www.coursera.org', url])
        return full_url


if __name__ == '__main__':
    process = CrawlerProcess({})

    process.crawl(CourseraSpider)
    process.start()