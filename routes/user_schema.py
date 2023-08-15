from pydantic import BaseModel, Field


class UserSchema(BaseModel):
    name: str = Field(...)

    class Config:
        schema_extra = {
            "example": {
                "name": " mostafa adam"
            }
        }
