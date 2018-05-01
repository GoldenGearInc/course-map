import json
from googletrans import Translator
from pathlib import Path


class CourseraTopicModeler:
    def __init__(self):
        self.translater = Translator()
        self.storage = str(Path(__file__).parent / 'result.json')

    def translate(self, text):
        translated_text = self.translater.translate(text).text
        return translated_text

    def recommend(self, topic):
        return ''

    def create_url(self, topic_name):
        words = topic_name.split(' ')
        search_query = '+'.join(words)
        search_page = f'https://www.coursera.org/courses?languages=en&' \
                      f'query={search_query}&userQuery={search_query}'
        return search_page

    def get_result(self):
        with open(self.storage, 'r') as f:
            data = json.load(f)
        return data
