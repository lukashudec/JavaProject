from locust import User, task

class Dummy(User):
    @task(1)
    def dummy(self):
        pass
