from pydantic import BaseModel, Field, Required
from typing import List, Optional


class ShareSchema(BaseModel):
    add_blogs: Optional[List[str]] = Field(...)
    remove_blogs: Optional[List[str]] = Field(...)

    class Config:
        schema_extra = {
            "example": {
                "add_blogs": ["64ce927f49e8ddc9698f51d2"],
                "remove_blogs": ["64ce927f49e8ddc9698f51d2"],

            }
        }
