from pydantic import BaseModel, Field


class BlogSchema(BaseModel):
    title: str = Field(...)
   # user_id: str = Field(...)

    class Config:
        schema_extra = {
            "example": {
                "title": "life",
                # "user_id": "64ce8c25ab4c1167dd47aaca",
            }
        }
