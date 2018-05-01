from flask import Flask, jsonify, request, make_response
from topic_modeling import CourseraTopicModeler
from recommend_scrappy.recommend_scrappy.spiders.coursera_scrapy import CrawlerProcess, CourseraSpider


app = Flask(__name__)
course_modeler = CourseraTopicModeler()
process = CrawlerProcess({})


@app.route('/recommend', methods=['POST'])
def recommend():
    text = request.json['title']
    input_text = course_modeler.translate(text)
    search_page = course_modeler.create_url(input_text)
    storage = course_modeler.storage
    process.crawl(CourseraSpider, search_page=search_page, storage=storage)
    process.start()
    result = course_modeler.get_result()
    return jsonify(result)


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
